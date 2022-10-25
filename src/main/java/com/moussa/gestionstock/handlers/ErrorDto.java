package com.moussa.gestionstock.handlers;

import com.moussa.gestionstock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private  Integer httpCode;
    private ErrorCodes errorCodes;
    private  String message;
    private List<String> errors = new ArrayList<>();
}
