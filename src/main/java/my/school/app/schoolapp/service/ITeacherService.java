package my.school.app.schoolapp.service;

import my.school.app.schoolapp.dto.FiltersDTO;
import my.school.app.schoolapp.dto.TeacherInsertDTO;
import my.school.app.schoolapp.dto.TeacherReadOnlyDTO;
import my.school.app.schoolapp.dto.TeacherUpdateDTO;
import my.school.app.schoolapp.exceptions.TeacherAlreadyExistsException;
import my.school.app.schoolapp.exceptions.TeacherDAOException;
import my.school.app.schoolapp.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {
    TeacherReadOnlyDTO insertTeacher(TeacherInsertDTO dto)
            throws TeacherDAOException, TeacherAlreadyExistsException;
    TeacherReadOnlyDTO updateTeacher(Integer id, TeacherUpdateDTO dto)
            throws TeacherDAOException, TeacherAlreadyExistsException, TeacherNotFoundException;
    void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    TeacherReadOnlyDTO getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;
    List<TeacherReadOnlyDTO> getAllTeachers() throws TeacherDAOException;
    List<TeacherReadOnlyDTO> getTeachersByLastname(String lastname) throws TeacherDAOException;
    List<TeacherReadOnlyDTO> getFilteredTeachers(FiltersDTO filters) throws TeacherDAOException;
}
