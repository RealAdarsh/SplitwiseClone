package com.example.splitwise.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponseDTO {
    private Long userId;
    private String userName;
    private String phone;
    // password skipped
    private ResponseStatus responseStatus;
    private String message;
}
