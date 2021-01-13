package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.data.UserEntity;
import hau.kute.spring.tutorial.springbootmicroservice.data.UsersRepository;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import hau.kute.spring.tutorial.springbootmicroservice.util.modelmapper.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /*
     private static List<UserRequestResponse> userRequestResponses = new ArrayList<>();

     private static int userCount = 3;

     static {
         userRequestResponses.add(new UserRequestResponse(1, "Adam", new Date(), DEFAULT_PASSWORD,
                         "adam@gmail.com"));
         userRequestResponses.add(new UserRequestResponse(2, "Eve", new Date(), DEFAULT_PASSWORD,
                         "eve@gmail.com"));
         userRequestResponses.add(new UserRequestResponse(3, "Jack", new Date(), DEFAULT_PASSWORD,
                         "jack@gmail.com"));
     }

     public List<UserRequestResponse> findAll() {
         return userRequestResponses;
     }

     public UserRequestResponse save(UserRequestResponse userRequestResponse) {
         userRequestResponse.setId(++userCount);
         userRequestResponse.setPassword(DEFAULT_PASSWORD);
         userRequestResponses.add(userRequestResponse);
         return userRequestResponse;
     }
    */

    public UserDTO save(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());

        UserEntity userEntity = UserModelMapper.parseFromDTOToEntity(userDTO);
        userEntity.setEncryptedPassword(DEFAULT_PASSWORD);
        usersRepository.save(userEntity);

        return UserModelMapper.parseFromEntityToDTO(userEntity);
    }

    /*
     public UserRequestResponse findOne(int id) {
         for(UserRequestResponse userRequestResponse : userRequestResponses) {
             if(userRequestResponse.getId() == id) {
                 return userRequestResponse;
             }
         }
         return null;
     }

     public UserRequestResponse deleteById(int id) {
         Iterator<UserRequestResponse> iterator = userRequestResponses.iterator();
         while (iterator.hasNext()) {
             UserRequestResponse userRequestResponse = iterator.next();
             if(userRequestResponse.getId() == id) {
                 iterator.remove();
                 return userRequestResponse;
             }
         }
         return null;
     }
     */

    private static final String DEFAULT_PASSWORD = "123456";
}
