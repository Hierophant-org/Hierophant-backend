package com.hierophant.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hierophant.model.AuthRequest;
import com.hierophant.model.User;
import com.hierophant.service.UserService;
import com.hierophant.util.JwtToken;

@RestController // RestController is a specific type of Controller that already assumes you're returning a @ResponseBody
@RequestMapping("/users") // all methods and endpoints exposed at http://localhost:5000/hierophant/users
@CrossOrigin(origins="http://hierophant-frontend-bucket.s3-website.us-east-2.amazonaws.com/")
public class UserController {
	// our controller needs to call its dependency which is our UserService
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtToken jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);

	// GET request that reads the id from the query parameter
	// ResponseEntity allows us to send back custom HTTP status & headers within the
	// HTTP response
	@GetMapping("/find") // if I send a get request to http://localhost:5000/hierophant/users/5, it will capture 5 and search the User table for it
	public ResponseEntity<Optional<User>> findById(@RequestParam("id") int id) {
		// call the service method, pass the capture id through, and return it as response entity with 200 OK status
		return ResponseEntity.ok(userService.findById(id));
	}

	// Create a method that fetches the path variable for finding a user by their username
	@GetMapping("/findBy")
	public ResponseEntity<Optional<User>> findByUsername(@RequestParam("username") String userName) {
		return ResponseEntity.ok(userService.findByUserName(userName));
	}
	
	// Create a method that fetches all users
	@GetMapping("/findAll")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	// Using post to accomadate create crud
	@PostMapping("/register") // The Valid annotation makes sure that User must comply with the restriction we set in the model
	public ResponseEntity<User> insert(@Valid @RequestBody User u) { // we're taking in the User object in the HTTP RequestBody
		System.out.println("THE OBJECT IS " + u.toString());
		System.out.println("it should be insert " + userService.insert(u));
		return ResponseEntity.ok(userService.insert(u));
	}

	// Using patch to accomadate update crud
	@PatchMapping("/update") // The Valid annotation makes sure that User must comply with the restriction we set in the model
	public ResponseEntity<User> update(@Valid @RequestBody User u) { // we're taking in the User object in the HTTP RequestBody
		return ResponseEntity.ok(userService.update(u));
	}

	// Using post to accomadate create crud
	@DeleteMapping("/delete/{id}") // The Valid annotation makes sure that User must comply with the restriction we set in the model
	public ResponseEntity<Void> deleteById(@PathVariable("id") int id) { // we're taking in the User object in the HTTP RequestBody
		// Untested
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/home")
	public String welcome() {
		return "The token worked y'all";
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception{
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
			);
			return jwtUtil.generateJwtToken(authRequest.getUsername());
		}
		catch(Exception ex) {
			throw new Exception("invalid username/passwrod");
		}
				
	}
}
