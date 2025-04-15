package my.school.app.schoolapp.exceptions;

import my.school.app.schoolapp.model.User;

import java.io.Serial;

public class UserNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(User user) {
        super("User with username: " + user.getUsername() + " not found");
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
