package com.example.splitwise.Controllers;

import com.example.splitwise.Dtos.ResponseStatus;
import com.example.splitwise.Dtos.SettleGroupRequestDTO;
import com.example.splitwise.Dtos.SettleGroupResponseDTO;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {
    public SettleGroupResponseDTO SettleGroup(SettleGroupRequestDTO requestDTO){
        SettleGroupResponseDTO responseDTO= new SettleGroupResponseDTO();
        responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        responseDTO.setMessage("Not implemented");

        return responseDTO;

    }
}
