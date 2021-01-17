package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

	UserDTO save(UserDTO user);

	UserDTO getUserByEmail(String email);
}
