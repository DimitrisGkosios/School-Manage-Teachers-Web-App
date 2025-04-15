package my.school.app.schoolapp.dao;

import my.school.app.schoolapp.exceptions.TeacherDAOException;
import my.school.app.schoolapp.model.Teacher;

import java.util.List;

public interface ITeacherDAO {

    // Basic services
    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(Integer id) throws TeacherDAOException;
    Teacher getById(Integer id) throws TeacherDAOException;
    List<Teacher> getAll() throws TeacherDAOException;

    // Queries
    Teacher getByUUID(String uuid) throws TeacherDAOException;
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;
    Teacher getTeacherByVat(String vat) throws TeacherDAOException;
    List<Teacher> getFilteredTeachers(String firstname, String lastname) throws TeacherDAOException;
}
