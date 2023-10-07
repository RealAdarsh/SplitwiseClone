package com.example.splitwise.Commands;

import com.example.splitwise.Controllers.GroupController;
import com.example.splitwise.Controllers.UserController;
import com.example.splitwise.Dtos.*;
import com.example.splitwise.Models.Transaction;
import com.example.splitwise.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserCommand implements Commands{
    private UserController userController;
    private long userId;

    @Autowired
    public GetUserCommand(UserController userController){
        this.userController=userController;
    }
    @Override
    public boolean canExecute(String search) {
        if (search.startsWith("getUser")==false) return false;

        if (search.split(" ").length!=2){
            return false;
        }

        String[] arr=search.split(" ");
        try{
            userId=Long.parseLong(arr[1]);
        }catch(NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        GetUserRequestDTO requestDTO=new GetUserRequestDTO();

        requestDTO.setUserId(userId);

        GetUserResponseDTO responseDTO=userController.getUser(requestDTO);

        if (responseDTO.getResponseStatus()== ResponseStatus.SUCCESS){
            System.out.println("User Id= "+ responseDTO.getUserId());
            System.out.println("User Name= "+ responseDTO.getUserName());
            System.out.println("User Phone= "+ responseDTO.getPhone());
        }else{
            System.out.println("GetUser failed: " + responseDTO.getMessage());
        }
    }
}
