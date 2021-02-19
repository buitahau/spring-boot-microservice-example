package hau.kute.spring.tutorial.springbootmicroservice.controller;

import hau.kute.spring.tutorial.springbootmicroservice.service.UserService;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class UserResource {

	@Autowired
	private UserService _userService;

//	@GetMapping("/{id}")
//	public UserRequestResponse retrieveUser(@PathVariable String userId) {
//
//		UserDTO user = _userService.getUserById(userId);
//
//		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass())
//						.retrieveAllUsers());
//
//		user.add(linkTo.withRel("all-users"));
//
//		return user;
//	}

	@PostMapping(value = "/users")
	public ResponseEntity<UserDTO> createUser(
					@RequestBody @Valid UserDTO userDTO) {

		userDTO = _userService.save(userDTO);

		URI location = _buildURIForUser(userDTO.getUserId());

		return ResponseEntity.created(location).body
						(userDTO);
	}

	@GetMapping(value = "/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") String
					userId) {
		UserDTO userDTO = _userService.getUserById(userId);

		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}

	private URI _buildURIForUser(String userId) {
		return  ServletUriComponentsBuilder.fromCurrentRequest().path(
						"/{id}").buildAndExpand(userId).toUri();
	}
}
