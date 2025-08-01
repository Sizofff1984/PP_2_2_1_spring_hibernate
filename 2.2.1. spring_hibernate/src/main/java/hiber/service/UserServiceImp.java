package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void addUser(User user) {
      userDao.addUser(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getAllUsers() {
      return userDao.getAllUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User findUserByCar(String model, int series) {
      return userDao.findUserByCar(model, series);
   }

   @Transactional
   @Override
   public void clearAllUsersAndCars() {
      userDao.clearAllUsersAndCars();
   }
}