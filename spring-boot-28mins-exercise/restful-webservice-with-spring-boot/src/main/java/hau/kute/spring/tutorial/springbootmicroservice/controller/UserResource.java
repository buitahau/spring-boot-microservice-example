package hau.kute.spring.tutorial.springbootmicroservice.controller;

import hau.kute.spring.tutorial.springbootmicroservice.bean.User;
import hau.kute.spring.tutorial.springbootmicroservice.exception.UserNotFoundException;
import hau.kute.spring.tutorial.springbootmicroservice.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService _userService;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {

		return _userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {

		User user = _userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(
							"Can not found user[id=" + id + "]");
		}

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass())
						.retrieveAllUsers());

		user.add(linkTo.withRel("all-users"));

		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(
					@Valid @RequestBody User user) {

		User savedUser = _userService.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
						"/{id}").buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {

		User user = _userService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException(
							"Can not found user[id=" + id + "]");
		}
	}
}
