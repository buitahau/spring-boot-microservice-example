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
	@GetMapping(produces = {
							MediaType.APPLICATION_XML_VALUE,
							MediaType.APPLICATION_JSON_VALUE
					})
	public MappingJacksonValue retrieveAllUsers() {

		List<UserRequestResponse> userRequestResponses = _userService.findAll();

		return dynamicFilteringResponse(userRequestResponses);
	}

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveAllUsersFiltering() {

		List<UserRequestResponse> userRequestResponses = _userService.findAll();

		Set<String> fields = new HashSet<>();
		fields.add("id");
		fields.add("name");

		return dynamicFilteringResponse(userRequestResponses, fields);
	}

	private MappingJacksonValue dynamicFilteringResponse(List<UserRequestResponse> userRequestResponses) {

		return dynamicFilteringResponse(userRequestResponses, null);
	}

	private MappingJacksonValue dynamicFilteringResponse(
					List<UserRequestResponse> userRequestResponses, Set<String> fields) {

		MappingJacksonValue mapping = new MappingJacksonValue(
						userRequestResponses);

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
			consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType
					.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType
							.APPLICATION_XML_VALUE}
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
