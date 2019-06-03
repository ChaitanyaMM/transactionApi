package com.transaction.zone.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.transaction.zone.Entities.User;
import com.transaction.zone.Exceptions.ResourceNotFoundException;
import com.transaction.zone.Util.ServerResponse;
import com.transaction.zone.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping("/api/v1/users")
@Api(value = "user Management System", description = "user operations management")

public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	ServerResponse<Object> server = new ServerResponse<Object>();
	Map<String, Object> response = new HashMap<String, Object>();

	@Autowired
	private UserService userService;

	@ApiOperation(value = "View a list of available employees", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty() || users == null) {
			response = server.getNotFoundResponse("Data Not Found", users);
		}
		response = server.getSuccessResponse("Uploded Successfully", users);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "Get an employee by Id")
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getEmployeeById(
			@ApiParam(value = "User id from which employee object will retrieve", required = true) @PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {

		if (userId == null) {

			response = server.getNotAceptableResponse("Invalid Input", userId);
		}

		Optional<User> user = userService.findById(userId);

		if (user == null) {
			throw new ResourceNotFoundException("NotFound");
		}

		response = server.getSuccessResponse("Uploded Successfully", user);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "Add an user")
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> createEmployee(
			@ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody User user) {

		if (user == null) {

			response = server.getNotAceptableResponse("Invalid Input", user);
		}

		user = userService.createuser(user);

		response = server.getSuccessResponse("User Created Successfully", user);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Update an employee")
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateEmployee(
			@ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable(value = "id") Long userId,
			@ApiParam(value = "Update employee object", required = true) @Valid @RequestBody User user)
			throws ResourceNotFoundException {
		Optional<User> fetchUser = userService.findById(userId);

		if (!fetchUser.isPresent()) {
			response = server.getNotFoundResponse("userNotFound", userId);

		} else {

			User updateUser = userService.updateuser(userId, user);
			response = server.getSuccessResponse("User Updated Successfully", updateUser);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@ApiOperation(value = "Delete an employee")
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteEmployee(
			@ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		Optional<User> user = userService.findById(userId);

		if (!user.isPresent()) {
			response = server.getNotFoundResponse("userNotFound", userId);

		}

		userService.delete(userId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}
}
