package hiber.service;

import hiber.model.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> getAllUsers();
    User findUserByCar(String model, int series);
    void clearAllUsersAndCars();
}