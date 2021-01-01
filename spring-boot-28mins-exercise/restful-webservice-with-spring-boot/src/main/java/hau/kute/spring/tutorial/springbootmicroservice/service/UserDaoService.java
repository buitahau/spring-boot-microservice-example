package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
     private static List<User> users = new ArrayList<>();

     private static int userCount = 3;

     private static final String DEFAULT_PASSWORD = "123456";

     static {
         users.add(new User(1, "Adam", new Date(), DEFAULT_PASSWORD));
         users.add(new User(2, "Eve", new Date(), DEFAULT_PASSWORD));
         users.add(new User(3, "Jack", new Date(), DEFAULT_PASSWORD));
     }

     public List<User> findAll() {
         return users;
     }

     public User save(User user) {
         user.setId(++userCount);
         user.setPassword(DEFAULT_PASSWORD);
         users.add(user);
         return user;
     }

     public User findOne(int id) {
         for(User user : users) {
             if(user.getId() == id) {
                 return user;
             }
         }
         return null;
     }

     public User deleteById(int id) {
         Iterator<User> iterator = users.iterator();
         while (iterator.hasNext()) {
             User user = iterator.next();
             if(user.getId() == id) {
                 iterator.remove();
                 return user;
             }
         }
         return null;
     }

}
