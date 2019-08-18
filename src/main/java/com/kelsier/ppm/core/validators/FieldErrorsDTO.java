package com.kelsier.ppm.core.validators;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class FieldErrorsDTO {

   private Map<String,String> fieldErrors;
}
