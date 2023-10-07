package com.example.splitwise.Services;

import com.example.splitwise.Models.User;
import com.example.splitwise.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User getUser(Long userId) throws Exception{
        Optional<User> userOptional=userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new Exception("User Not found");
        }

        return userOptional.get();
    }

    public User registerUser(User user){
        User savedUser=userRepository.save(user);
        return savedUser;
    }
}
