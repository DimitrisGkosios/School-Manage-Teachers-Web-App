package my.school.app.schoolapp.service;

import my.school.app.schoolapp.dto.InsertUserDTO;
import my.school.app.schoolapp.exceptions.UserDAOException;
import my.school.app.schoolapp.exceptions.UserNotFoundException;
import my.school.app.schoolapp.model.User;

public interface IUserService {
    User insertUser(InsertUserDTO dto) throws UserDAOException;
    User getUserByUsername(String username) throws UserNotFoundException, UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
