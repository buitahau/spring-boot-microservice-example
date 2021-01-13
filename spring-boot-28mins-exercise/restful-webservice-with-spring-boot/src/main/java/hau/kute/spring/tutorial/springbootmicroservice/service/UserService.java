package hau.kute.spring.tutorial.springbootmicroservice.service;

import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;

public interface UserService {

	UserDTO save(UserDTO user);
}
