package com.example.splitwise.Commands;

public class CreateExpenseCommand implements Commands{
    @Override
    public boolean canExecute(String search) {
        return false;
    }

    @Override
    public void execute() {

    }
}
