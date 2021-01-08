package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.bean.User;
import hau.kute.spring.tutorial.springbootmicroservice.data.UserEntity;
import hau.kute.spring.tutorial.springbootmicroservice.data.UsersRepository;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import hau.kute.spring.tutorial.springbootmicroservice.util.modelmapper.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

     private static List<User> users = new ArrayList<>();

     private static int userCount = 3;

     private static final String DEFAULT_PASSWORD = "123456";

     static {
         users.add(new User(1, "Adam", new Date(), DEFAULT_PASSWORD,
                         "adam@gmail.com"));
         users.add(new User(2, "Eve", new Date(), DEFAULT_PASSWORD,
                         "eve@gmail.com"));
         users.add(new User(3, "Jack", new Date(), DEFAULT_PASSWORD,
                         "jack@gmail.com"));
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

    public UserDTO save(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());

        UserEntity userEntity = UserModelMapper.parseFromDTOToEntity(userDTO);
        userEntity.setEncryptedPassword(DEFAULT_PASSWORD);
        usersRepository.save(userEntity);

        return userDTO;
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
