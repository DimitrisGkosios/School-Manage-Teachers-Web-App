package my.school.app.schoolapp.authentication;

import my.school.app.schoolapp.dao.IUserDAO;
import my.school.app.schoolapp.dao.UserDAOImpl;
import my.school.app.schoolapp.dto.UserLoginDTO;
import my.school.app.schoolapp.exceptions.UserDAOException;
import my.school.app.schoolapp.service.IUserService;
import my.school.app.schoolapp.service.UserServiceImpl;

public class AuthenticationProvider {
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {}

    public static boolean authenticate(UserLoginDTO userLoginDTO) throws UserDAOException {
        return userService.isUserValid(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }
}
