package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.data.UserEntity;
import hau.kute.spring.tutorial.springbootmicroservice.data.UsersRepository;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import hau.kute.spring.tutorial.springbootmicroservice.util.modelmapper.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
        userDTO.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        UserEntity userEntity = UserModelMapper.parseFromDTOToEntity(userDTO);

        usersRepository.save(userEntity);

        return UserModelMapper.parseFromEntityToDTO(userEntity);
    }

    @Override
    public UserDTO getUserByEmail(String email) {

        UserEntity userEntity = usersRepository.findByEmail(email);

        if(userEntity == null) {
            throw new UsernameNotFoundException("Could not found user with " +
                            "email = " + email);
        }

        return UserModelMapper.parseFromEntityToDTO(userEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
                    throws UsernameNotFoundException {

        UserEntity userEntity = usersRepository.findByEmail(userName);

        if(userEntity == null) {
            throw new UsernameNotFoundException("Could not found user with " +
                            "email = " + userName);
        }

        return new User(userEntity.getEmail(), userEntity
                        .getEncryptedPassword(), true, true, true, true, new
                        ArrayList<>());
    }
}
