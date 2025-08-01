package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void addUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> getAllUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public User findUserByCar(String model, int series) {
      try {
         TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                 "from User where car.model = :model and car.series = :series", User.class);
         query.setParameter("model", model);
         query.setParameter("series", series);
         return query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }

   @Override
   public void clearAllUsersAndCars() {
      Session session = sessionFactory.getCurrentSession();
      session.createQuery("delete from User").executeUpdate();
      session.createQuery("delete from Car").executeUpdate();
   }
}