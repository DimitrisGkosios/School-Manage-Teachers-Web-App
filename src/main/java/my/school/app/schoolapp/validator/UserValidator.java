package my.school.app.schoolapp.validator;

import my.school.app.schoolapp.dao.IUserDAO;
import my.school.app.schoolapp.dao.UserDAOImpl;
import my.school.app.schoolapp.dto.BaseUserDTO;
import my.school.app.schoolapp.exceptions.UserDAOException;
import my.school.app.schoolapp.service.IUserService;
import my.school.app.schoolapp.service.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class UserValidator<T> {

    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private UserValidator() {}

    public static <T extends BaseUserDTO> Map<String, String > validate(T dto)
            throws UserDAOException {
        Map<String, String> errors = new HashMap<>();

        if (!dto.getPassword().equals(dto.getConfirmedPassword())) {
            errors.put("confirmPassword", "Το password και το confirmedPassword δεν είναι ίδια.");
        }

        if (dto.getPassword().length() < 5 || dto.getPassword().length() > 32 ) {
            errors.put("password", "Το password πρέπει να είναι μεταξύ 5 και 32");
        }

        if (dto.getUsername().matches("^.*\\s+.*$")) {
            errors.put("username", "Το username δεν πρέπει να περιλαμβάνει κενά");
        }

        if (dto.getPassword().matches("^.*\\s+.*$")) {
            errors.put("password", "Το password δεν πρέπει να περιλαμβάνει κενά");
        }

        if (userService.isEmailExists(dto.getUsername())) {
            errors.put("username", "Το e-mail/username υπάρχει ήδη");
        }

        return errors;
    }
}
