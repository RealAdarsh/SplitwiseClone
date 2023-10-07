package com.example.splitwise.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// NO @Entity as no table for transactions

public class Transaction {
    private String from;
    private String to;
    private int amount;

    public String toString(){
        return from + " should pay " + amount + " to " + to + "/n";
    }
}
