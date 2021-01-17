package hau.kute.spring.tutorial.springbootmicroservice.controller;

import hau.kute.spring.tutorial.springbootmicroservice.service.UserService;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService _userService;

	/*
	@GetMapping("/{id}")
	public UserRequestResponse retrieveUser(@PathVariable int id) {

		UserRequestResponse user = _userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(
							"Can not found user[id=" + id + "]");
		}

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass())
						.retrieveAllUsers());

		user.add(linkTo.withRel("all-users"));

		return user;
	}
	*/

	@PostMapping(
			consumes = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE},
			produces = {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE}
	)
	public ResponseEntity<UserDTO> createUser(
					@RequestBody @Valid UserDTO userDTO) {

		userDTO = _userService.save(userDTO);

		URI location = _buildURIForUser(userDTO.getUserId());

		return ResponseEntity.created(location).body
						(userDTO);
	}

	private URI _buildURIForUser(String userId) {
		return  ServletUriComponentsBuilder.fromCurrentRequest().path(
						"/{id}").buildAndExpand(userId).toUri();
	}
}
