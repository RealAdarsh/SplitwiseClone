package com.example.splitwise.Commands;

import com.example.splitwise.Controllers.UserController;
import com.example.splitwise.Dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand implements Commands{
    private UserController userController;
    private String userName;
    private String phoneNumber;
    private String password;
    private long userId;

    @Autowired
    public RegisterUserCommand(UserController userController){
        this.userController=userController;
    }
    @Override
    public boolean canExecute(String search) {
        if (search.startsWith("registerUser")==false) return false;

        if (search.split(" ").length!=4){
            return false;
        }

        String[] arr=search.split(" ");
        userName=arr[1];
        phoneNumber=arr[2];
        password=arr[3];



        return true;
    }

    @Override
    public void execute() {

        RegisterUserRequestDTO requestDTO=new RegisterUserRequestDTO();
        requestDTO.setUserName(userName);
        requestDTO.setPhone(phoneNumber);
        requestDTO.setPassword(password);

        RegisterUserResponseDTO responseDTO=userController.registerUser(requestDTO);
        if (responseDTO.getResponseStatus()==ResponseStatus.SUCCESS){

        }else{
            System.out.println("RegisterUser Failure: " +  responseDTO.getMessage());
        }
    }
}
