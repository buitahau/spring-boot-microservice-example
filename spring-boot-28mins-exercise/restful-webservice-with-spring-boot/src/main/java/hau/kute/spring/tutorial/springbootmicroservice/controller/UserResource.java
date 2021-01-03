package hau.kute.spring.tutorial.springbootmicroservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import hau.kute.spring.tutorial.springbootmicroservice.bean.User;
import hau.kute.spring.tutorial.springbootmicroservice.exception.UserNotFoundException;
import hau.kute.spring.tutorial.springbootmicroservice.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService _userService;

	@GetMapping(value = "/users",
					produces = {
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_VALUE
					})
	public MappingJacksonValue retrieveAllUsers() {

		List<User> users = _userService.findAll();

		return dynamicFilteringResponse(users);
	}

	@GetMapping("/users-filtering")
	public MappingJacksonValue retrieveAllUsersFiltering() {

		List<User> users = _userService.findAll();

		Set<String> fields = new HashSet<>();
		fields.add("id");
		fields.add("name");

		return dynamicFilteringResponse(users, fields);
	}

	private MappingJacksonValue dynamicFilteringResponse(List<User> users) {

		return dynamicFilteringResponse(users, null);
	}

	private MappingJacksonValue dynamicFilteringResponse(
					List<User> users, Set<String> fields) {

		MappingJacksonValue mapping = new MappingJacksonValue(users);

		SimpleBeanPropertyFilter simpleBeanPropertyFilter =
						SimpleBeanPropertyFilter.serializeAll();

		if (fields != null && fields.size() > 0) {
			simpleBeanPropertyFilter =
							SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		}

		FilterProvider filters =
						new SimpleFilterProvider().addFilter("UserFilter",
										simpleBeanPropertyFilter);

		mapping.setFilters(filters);

		return mapping;
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
