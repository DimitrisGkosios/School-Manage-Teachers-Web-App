package my.school.app.schoolapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.school.app.schoolapp.dao.ITeacherDAO;
import my.school.app.schoolapp.dao.TeacherDAOImpl;
import my.school.app.schoolapp.exceptions.TeacherDAOException;
import my.school.app.schoolapp.exceptions.TeacherNotFoundException;
import my.school.app.schoolapp.service.ITeacherService;
import my.school.app.schoolapp.service.TeacherServiceImpl;

import java.io.IOException;

@WebServlet("/school-app/teachers/delete")
public class TeacherDeleteController extends HttpServlet {

    ITeacherDAO teacherDAO = new TeacherDAOImpl();
    ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
    String message = "";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            teacherService.deleteTeacher(id);
            req.setAttribute("id", id);
            req.getRequestDispatcher("/WEB-INF/jsp/teacher-deleted.jsp").forward(req, resp);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            message = e.getMessage();
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/jsp/teachers.jsp").forward(req, resp);
        }
    }
}
