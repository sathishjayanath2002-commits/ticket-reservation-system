package dao;

import exceptions.InvalidLoginException;
import model.User;

/**
 * Data access contract for User-related DB operations.
 * Member working on Authentication module implements this.
 */
public interface UserDAO {
    User login(String email, String password) throws InvalidLoginException;
    boolean register(User user);
    boolean emailExists(String email);
}
