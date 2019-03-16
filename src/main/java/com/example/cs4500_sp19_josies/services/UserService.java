package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.repositories.UserRepository;

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

	@RequestMapping(method = RequestMethod.GET)
	public User findUserByUsername(@RequestParam(value="username") String username) {
		return userRepository.findByUsername(username);
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
		return userRepository.save(user);
	}

	@DeleteMapping("/api/users/{userId}")
	public void deleteUser(
			@PathVariable("userId") Integer id) {
		userRepository.deleteById(id);
	}
}
