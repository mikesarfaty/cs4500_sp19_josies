package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.repositories.UserRepository;

@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findUserById(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

}