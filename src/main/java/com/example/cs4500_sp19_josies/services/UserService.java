package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.repositories.UserRepository;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins="*")
public class UserService {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/api/users")
	public List<User> findAllUser() {
		return userRepository.findAllUsers();
	}

	@GetMapping("/api/users/{userId}")
	public User findUserById(
			@PathVariable("userId") Integer id) {
		return userRepository.findUserById(id);
	}

	@PostMapping("/api/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/api/users/{userId}")
	public User updateUser(
			@PathVariable("userId") Integer id,
			@RequestBody User userUpdates) {
		User user = userRepository.findUserById(id);
		user.setFirstName(userUpdates.getFirstName());
		user.setLastName(userUpdates.getLastName());
		user.setUsername(userUpdates.getUsername());
		user.setPassword(userUpdates.getPassword());
		user.setRole(userUpdates.getRole());
		user.setMonth(userUpdates.getMonth());
		user.setDay(userUpdates.getDay());
		user.setYear(userUpdates.getYear());
		user.setCity(userUpdates.getCity());
		user.setStreet(userUpdates.getStreet());
		user.setState(userUpdates.getState());
		user.setZip(userUpdates.getZip());
		user.setServices(userUpdates.getServices());
		user.setEmail(userUpdates.getEmail());
		return userRepository.save(user);
	}

	@DeleteMapping("/api/users/{userId}")
	public void deleteUser(
			@PathVariable("userId") Integer id) {
		userRepository.deleteById(id);
	}

	/*
	 * Methods for registration.
	 */
	@PostMapping("/api/register")
	public User register(
			@RequestBody User user,
			HttpSession session) {
		User newUser = userRepository.save(user);
		session.setAttribute("currentUser", newUser);
		return user;
	}

}
