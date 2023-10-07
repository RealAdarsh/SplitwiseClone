package com.example.splitwise.Commands;

import com.example.splitwise.Controllers.GroupController;
import com.example.splitwise.Dtos.ResponseStatus;
import com.example.splitwise.Dtos.SettleGroupRequestDTO;
import com.example.splitwise.Dtos.SettleGroupResponseDTO;
import com.example.splitwise.Models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleGroupCommand implements Commands{

    private GroupController groupController;
    private Long groupId;

    @Autowired
    public SettleGroupCommand(GroupController groupController){
        this.groupController=groupController;
    }
    @Override
    public boolean canExecute(String search) {
        if (search.startsWith("settleGroup")==false) return false;

        if (search.split(" ").length!=2){
            return false;
        }

        String[] arr=search.split(" ");
        try{
            groupId=Long.parseLong(arr[1]);
        }catch(NumberFormatException numberFormatException){
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        SettleGroupRequestDTO requestDTO=new SettleGroupRequestDTO();

        requestDTO.setGroupId(groupId);

        SettleGroupResponseDTO responseDTO=groupController.SettleGroup(requestDTO);

        if (responseDTO.getResponseStatus()== ResponseStatus.SUCCESS){
            List< Transaction> transactionList = responseDTO.getTransactions();

            for (Transaction transaction: transactionList){
                System.out.println(transaction);
            }
        }else{
            System.out.println("Settle Group failed: " + responseDTO.getMessage());
        }
    }
}
