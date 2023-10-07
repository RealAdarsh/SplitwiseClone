package com.example.splitwise.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {
    private List<Commands> registeredCommands=new ArrayList<>();

    @Autowired
    public CommandRegistry(GetUserCommand getUserCommand, RegisterUserCommand registerUserCommand, SettleGroupCommand settleGroupCommand){
        registeredCommands.add(getUserCommand);
        registeredCommands.add(registerUserCommand);
        registeredCommands.add(settleGroupCommand);
    }

    public void process(String input){
        for (Commands registeredCommand : registeredCommands){
            if (registeredCommand.canExecute(input)==true){
                registeredCommand.execute();
                break;
            }
        }
    }
}
