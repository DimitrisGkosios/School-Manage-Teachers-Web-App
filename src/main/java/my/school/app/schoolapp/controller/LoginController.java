package my.school.app.schoolapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import my.school.app.schoolapp.authentication.AuthenticationProvider;
import my.school.app.schoolapp.dao.IUserDAO;
import my.school.app.schoolapp.dao.UserDAOImpl;
import my.school.app.schoolapp.dto.UserLoginDTO;
import my.school.app.schoolapp.exceptions.UserDAOException;
import my.school.app.schoolapp.exceptions.UserNotFoundException;
import my.school.app.schoolapp.service.IUserService;
import my.school.app.schoolapp.service.UserServiceImpl;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final IUserDAO userDAO = new UserDAOImpl();
    private final IUserService userService = new UserServiceImpl(userDAO);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ADMIN_TIMEOUT = 30 * 60; // 30 mins
        // Data binding
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);

        boolean principleIsAuthenticated = false;
        try {
            principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);

            if (!principleIsAuthenticated) {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
                return;
            }

            HttpSession oldSession = request.getSession(false);
            // no session exists before
            // we explicitly create a session with getSession(true)
            // that is that tomcat creates a jsessionid, but does not store it to backend
            // session object. it is just a placeholder. to create a session object and
            // thus create state at backend you have to call getSession(true) or getSession()
            if (oldSession != null) {
                oldSession.invalidate(); // Destroy attacker's session
            }
            HttpSession session = request.getSession(true); // create a session object
            session.setAttribute("authenticated", true);
            session.setAttribute("username", username);
            session.setAttribute("role", userService.getUserByUsername(username).getRoleType().name());

            if (session.getAttribute("role").equals("ADMIN")) { // overwrites web.xml
                session.setMaxInactiveInterval(ADMIN_TIMEOUT);  // Admin get 30-min sessions
            }

            //response.sendRedirect(request.getContextPath() + "/school-app/dashboard");
            response.sendRedirect(request.getContextPath() + "/school-app/teachers/view");


//                response.sendRedirect(request.getContextPath() + "/login?isError=true");

        } catch (UserDAOException | UserNotFoundException e) {
            //response.sendRedirect(request.getContextPath() + "/login?isError=true");
            request.setAttribute("error", "Authentication Error");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }
}
