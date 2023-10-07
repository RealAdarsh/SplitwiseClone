package com.example.splitwise;

import com.example.splitwise.Commands.CommandRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

    private CommandRegistry commandRegistry;

    @Autowired
    public SplitwiseApplication(CommandRegistry commandRegistry){
        this.commandRegistry=commandRegistry;
    }

    public static void main(String[] args) {

        SpringApplication.run(SplitwiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner=new Scanner(System.in);

        while(true){
            String input=scanner.nextLine();
            commandRegistry.process(input);
        }
    }

}
