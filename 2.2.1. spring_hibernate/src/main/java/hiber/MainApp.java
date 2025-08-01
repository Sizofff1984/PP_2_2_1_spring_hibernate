package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      try (AnnotationConfigApplicationContext context =
                   new AnnotationConfigApplicationContext(AppConfig.class)) {

         UserService userService = context.getBean(UserService.class);


         userService.clearAllUsersAndCars();


         List<User> testUsers = createTestUsers();


         testUsers.forEach(userService::addUser);


         printAllUsers(userService);


         findAndPrintUserByCar(userService, "BMW", 789);
      }
   }

   private static List<User> createTestUsers() {
      Car car1 = new Car("Toyota", 123);
      Car car2 = new Car("Honda", 456);
      Car car3 = new Car("BMW", 789);
      Car car4 = new Car("Mercedes", 321);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car4);

      return List.of(user1, user2, user3, user4);
   }

   private static void printAllUsers(UserService userService) {
      System.out.println("===== Все пользователи =====");
      userService.getAllUsers().forEach(MainApp::printUserInfo);
      System.out.println("===========================");
   }

   private static void findAndPrintUserByCar(UserService userService, String model, int series) {
      System.out.printf("\nПоиск пользователя с автомобилем %s %d:\n", model, series);
      User user = userService.findUserByCar(model, series);

      if (user != null) {
         printUserInfo(user);
      } else {
         System.out.println("Пользователь не найден");
      }
   }

   private static void printUserInfo(User user) {
      System.out.println("\nИнформация о пользователе:");
      System.out.println("ID: " + user.getId());
      System.out.println("Имя: " + user.getFirstName());
      System.out.println("Фамилия: " + user.getLastName());
      System.out.println("Email: " + user.getEmail());

      Car car = user.getCar();
      if (car != null) {
         System.out.println("Автомобиль: " + car.getModel() + " " + car.getSeries());
      } else {
         System.out.println("Автомобиль: не указан");
      }
   }
}