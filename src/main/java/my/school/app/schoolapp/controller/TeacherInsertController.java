package my.school.app.schoolapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import my.school.app.schoolapp.dao.CityDAOImpl;
import my.school.app.schoolapp.dao.ICityDAO;
import my.school.app.schoolapp.dao.ITeacherDAO;
import my.school.app.schoolapp.dao.TeacherDAOImpl;
import my.school.app.schoolapp.dto.TeacherInsertDTO;
import my.school.app.schoolapp.dto.TeacherReadOnlyDTO;
import my.school.app.schoolapp.exceptions.TeacherAlreadyExistsException;
import my.school.app.schoolapp.exceptions.TeacherDAOException;
import my.school.app.schoolapp.model.City;
import my.school.app.schoolapp.model.Teacher;
import my.school.app.schoolapp.service.CityServiceImpl;
import my.school.app.schoolapp.service.ICityService;
import my.school.app.schoolapp.service.ITeacherService;
import my.school.app.schoolapp.service.TeacherServiceImpl;
import my.school.app.schoolapp.validator.TeacherValidator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/school-app/teachers/insert")
public class TeacherInsertController extends HttpServlet {
    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    ICityDAO cityDAO = new CityDAOImpl();
    ICityService cityService = new CityServiceImpl(cityDAO);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Always get fresh cities list
            List<City> cities = cityService.getAllCities();
            req.setAttribute("cities", cities);

            // Check for any persisted form data from previous POST
            if (req.getSession().getAttribute("formData") != null) {
                @SuppressWarnings("unchecked")
                Map<String, Object> formData = (Map<String, Object>) req.getSession().getAttribute("formData");

                // Transfer all attributes to request scope
                formData.forEach(req::setAttribute);

                // Clear session data
                req.getSession().removeAttribute("formData");
            }

            req.getRequestDispatcher("/WEB-INF/jsp/teacher-insert.jsp").forward(req, resp);

        } catch (SQLException e) {
            handleError(req, resp, "Error retrieving cities: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Create a map to hold all form data and errors
        Map<String, Object> formData = new HashMap<>();

        // Data binding
        TeacherInsertDTO insertDTO = new TeacherInsertDTO(
                req.getParameter("firstname"),
                req.getParameter("lastname"),
                req.getParameter("vat"),
                req.getParameter("fathername"),
                req.getParameter("phoneNum"),
                req.getParameter("email"),
                req.getParameter("street"),
                req.getParameter("streetNum"),
                req.getParameter("zipcode"),
                req.getParameter("cityId") != null ? Integer.parseInt(req.getParameter("cityId")) : 0
        );

        formData.put("insertDTO", insertDTO);

        try {
            // Validate DTO
            Map<String, String> errors = TeacherValidator.validate(insertDTO);

            if (!errors.isEmpty()) {
                // Add all error messages to formData
                errors.forEach((field, message) ->
                        formData.put(field + "Message", message));

                // Persist form data in session for the redirect
                req.getSession().setAttribute("formData", formData);
                resp.sendRedirect(req.getContextPath() + "/school-app/teachers/insert");
                return;
            }

            // Call service if validation passed
            TeacherReadOnlyDTO readOnlyDTO = teacherService.insertTeacher(insertDTO);
            req.getSession().setAttribute("teacherInfo", readOnlyDTO);

            // Clear any previous form data from session

            resp.sendRedirect(req.getContextPath() + "/school-app/teacher-inserted");

        } catch (IOException | TeacherDAOException | TeacherAlreadyExistsException e) {
            formData.put("errorMessage", e.getMessage());
            req.getSession().setAttribute("formData", formData);
            resp.sendRedirect(req.getContextPath() + "/school-app/teachers/insert");
        } catch (NumberFormatException e) {
            formData.put("errorMessage", "Invalid city selection");
            req.getSession().setAttribute("formData", formData);
            resp.sendRedirect(req.getContextPath() + "/school-app/teachers/insert");
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("errorMessage", message);
        req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
    }
}