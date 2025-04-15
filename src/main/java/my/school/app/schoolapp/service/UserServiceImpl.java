package my.school.app.schoolapp.service;

import my.school.app.schoolapp.dao.IUserDAO;
import my.school.app.schoolapp.dto.InsertUserDTO;
import my.school.app.schoolapp.exceptions.UserDAOException;
import my.school.app.schoolapp.exceptions.UserNotFoundException;
import my.school.app.schoolapp.mapper.Mapper;
import my.school.app.schoolapp.model.User;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(InsertUserDTO dto) throws UserDAOException {
        User user;

        try {
            user = Mapper.mapToUser(dto);
            // log
            return userDAO.insert(user);
        } catch (UserDAOException e) {
            // e.printStackTrace();
            // log
            // rollback
            throw e;
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException, UserDAOException {
        User user;

        try {
            user = userDAO.getByUsername(username);

            if (user == null) {
                throw new UserNotFoundException("User with username: " + username + " not found");
            }
            return user;
        } catch (UserDAOException e) {
            e.printStackTrace();
            // log
            // rollback
            throw e;
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        try {
            // Logging
            return userDAO.isUserValid(username, password);
        } catch (UserDAOException e) {
            e.printStackTrace();
            // log
            throw e;
        }
    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        try {
            // Logging
            return userDAO.isEmailExists(username);
        } catch (UserDAOException e) {
            e.printStackTrace();
            // log
            throw e;
        }
    }

//    private User mapToUser(InsertUserDTO dto) {
//        return new User(null, dto.getUsername(), dto.getPassword(), RoleType.valueOf(dto.getRole()));
//    }
}