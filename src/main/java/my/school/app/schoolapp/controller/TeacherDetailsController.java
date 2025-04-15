package my.school.app.schoolapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.school.app.schoolapp.dao.ITeacherDAO;
import my.school.app.schoolapp.dao.TeacherDAOImpl;
import my.school.app.schoolapp.dto.TeacherReadOnlyDTO;
import my.school.app.schoolapp.exceptions.TeacherDAOException;
import my.school.app.schoolapp.exceptions.TeacherNotFoundException;
import my.school.app.schoolapp.service.ITeacherService;
import my.school.app.schoolapp.service.TeacherServiceImpl;

import java.io.IOException;

@WebServlet("/school-app/teachers/details")
public class TeacherDetailsController extends HttpServlet {

    private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private final ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing teacher ID");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            TeacherReadOnlyDTO teacher = teacherService.getTeacherById(id);
            req.setAttribute("teacher", teacher);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-details.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid teacher ID format");
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}
