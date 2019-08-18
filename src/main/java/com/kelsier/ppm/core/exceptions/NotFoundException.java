package com.kelsier.ppm.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
}
