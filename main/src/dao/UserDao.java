package dao;

import model.User;

import java.util.Set;

public interface UserDao {
    User createUser(User user);
    User readUserById(int userId);
    Set<User> readAllUsers();
    boolean updateUser(User user);
    boolean deleteUser(int userId);

}
