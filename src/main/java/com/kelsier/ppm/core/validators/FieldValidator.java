package com.kelsier.ppm.core.validators;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

public class FieldValidator  {

   public static ResponseEntity<FieldErrorsDTO> getFieldErrors(BindingResult result){
        Map<String,String> fieldErrorMap = result.getFieldErrors().stream()
                .collect(Collectors.toMap(field -> field.getField(),field -> field.getDefaultMessage()));
        return new ResponseEntity<FieldErrorsDTO>(new FieldErrorsDTO(fieldErrorMap),HttpStatus.BAD_REQUEST);

    }
}
