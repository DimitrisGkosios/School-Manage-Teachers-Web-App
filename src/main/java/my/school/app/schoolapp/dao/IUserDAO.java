package my.school.app.schoolapp.dao;

import my.school.app.schoolapp.exceptions.UserDAOException;
import my.school.app.schoolapp.model.User;

public interface IUserDAO {
    User insert(User user) throws UserDAOException;
    // User update(User user) throws UserDAOException;
    // void delete(int id) throws UserDAOException;
    // List<User> getAll()
    User getByUsername(String username) throws UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
