package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.data.UserEntity;
import hau.kute.spring.tutorial.springbootmicroservice.data.UsersRepository;
import hau.kute.spring.tutorial.springbootmicroservice.exception.UserNotFoundException;
import hau.kute.spring.tutorial.springbootmicroservice.shared.AlbumDTO;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import hau.kute.spring.tutorial.springbootmicroservice.util.modelmapper.UserModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository,
                    BCryptPasswordEncoder bCryptPasswordEncoder,
                    RestTemplate restTemplate) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.restTemplate = restTemplate;
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
    public UserDTO getUserById(String userId) {
        UserEntity userEntity = usersRepository.findByUserId(userId);

        if(userEntity == null) {
            throw new UserNotFoundException(
                            "Could not found user with userId = " + userId);
        }
        UserDTO userDTO = UserModelMapper.parseFromEntityToDTO(userEntity);

        String albumsUrl = String.format("http://ALBUMS-WS/users/%s/albums",
                        userId);

        ResponseEntity<List<AlbumDTO>> albumsListResponses = restTemplate
                        .exchange(albumsUrl, HttpMethod.GET, null, new
                                        ParameterizedTypeReference<List<AlbumDTO>>() {});

        List<AlbumDTO> albumsList = albumsListResponses.getBody();

        userDTO.setAlbums(albumsList);

        return userDTO;
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
