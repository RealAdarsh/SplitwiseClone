package com.example.splitwise.Commands;

public interface Commands {
    boolean canExecute(String search);
    void execute();
}
