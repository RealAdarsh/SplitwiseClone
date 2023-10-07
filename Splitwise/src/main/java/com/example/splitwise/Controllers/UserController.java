package com.example.splitwise.Controllers;

import com.example.splitwise.Commands.RegisterUserCommand;
import com.example.splitwise.Dtos.*;
import com.example.splitwise.Models.User;
import com.example.splitwise.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.ExportException;

// 5 steps
// 1. Search Maven Repository for spring boot starter web
// 2. update pom and refresh maven
// 3. change controller to RestController
// 4. update methods with get or post mapping
// 5. Body or Params @RequestBody @RequestParams

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/user/register")
    public RegisterUserResponseDTO registerUser(@RequestBody() RegisterUserRequestDTO requestDTO){
        RegisterUserResponseDTO responseDTO= new RegisterUserResponseDTO();
        try{
            User user= new User();
            user.setUserName(requestDTO.getUserName());
            user.setPassword(requestDTO.getPassword());
            user.setPhone(requestDTO.getPhone());
            User savedUser= userService.registerUser(user);
            responseDTO.setUserId(savedUser.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("Registered User");
        }catch (Exception ex){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
        }
        return responseDTO;
    }
    @PostMapping("/user/get/")
            public GetUserResponseDTO getUser(@RequestBody() GetUserRequestDTO getUserRequestDTO){
        GetUserResponseDTO responseDTO=new GetUserResponseDTO();
        try{
            User user= userService.getUser(getUserRequestDTO.getUserId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("User Retrieved successfully");
            responseDTO.setUserId(user.getId());
            responseDTO.setUserName(user.getUserName());
            responseDTO.setPhone(user.getPhone());

        }catch (Exception ex){
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(ex.getMessage());
        }

        return responseDTO;
    }
}
