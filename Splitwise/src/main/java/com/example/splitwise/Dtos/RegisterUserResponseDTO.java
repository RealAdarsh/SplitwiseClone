package com.example.splitwise.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserResponseDTO {
    private Long userId;
    private ResponseStatus responseStatus;
    private String message;
}
