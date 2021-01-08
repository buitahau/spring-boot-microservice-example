package hau.kute.spring.tutorial.springbootmicroservice.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import hau.kute.spring.tutorial.springbootmicroservice.bean.User;
import hau.kute.spring.tutorial.springbootmicroservice.service.UserService;
import hau.kute.spring.tutorial.springbootmicroservice.shared.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService _userService;

	@GetMapping(produces = {
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_VALUE
					})
//	public MappingJacksonValue retrieveAllUsers() {
//
//		List<User> users = _userService.findAll();
//
//		return dynamicFilteringResponse(users);
//	}
//
//	@GetMapping("/filtering")
//	public MappingJacksonValue retrieveAllUsersFiltering() {
//
//		List<User> users = _userService.findAll();
//
//		Set<String> fields = new HashSet<>();
//		fields.add("id");
//		fields.add("name");
//
//		return dynamicFilteringResponse(users, fields);
//	}

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

//	@GetMapping("/{id}")
//	public User retrieveUser(@PathVariable int id) {
//
//		User user = _userService.findOne(id);
//		if (user == null) {
//			throw new UserNotFoundException(
//							"Can not found user[id=" + id + "]");
//		}
//
//		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass())
//						.retrieveAllUsers());
//
//		user.add(linkTo.withRel("all-users"));
//
//		return user;
//	}

	@PostMapping()
	public ResponseEntity<User> createUser(
					@Valid @RequestBody User user) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies
						.STRICT);

		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		_userService.save(userDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
						"/{id}").buildAndExpand("idinhere").toUri();

		return ResponseEntity.created(location).build();
	}

//	@DeleteMapping("/{id}")
//	public void deleteUser(@PathVariable int id) {
//
//		User user = _userService.deleteById(id);
//		if (user == null) {
//			throw new UserNotFoundException(
//							"Can not found user[id=" + id + "]");
//		}
//	}
}
