package com.sboxnw.monitoring.exception;

import com.sboxnw.monitoring.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) Margo Networks Private Limited - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kunal Utage <kunal.utage@catseyetech.in>, September 2017.
 *
 * This class can be used to send validation messages.
 *
 * Here we need to set the field and error message
 * Example:
 *
 * ValidationMsg vError = new ValidationMsg();
 * return vError.sendValidationMessage("field", "message");
 */
public class ValidationMsg extends BaseController {

    // Send Single Validation message
    public ResponseEntity<?> sendValidationMessage(String field, String message, HttpStatus httpStatus){

        /////////////////////////////////////////////////////////
        // Validation

        ErrorResource errorResource = new ErrorResource();
        FieldErrorResource fieldErrorResource = null;
        List<FieldErrorResource> fieldErrors = new ArrayList<>();

        fieldErrorResource = new FieldErrorResource();
        fieldErrorResource.setField(field);
        fieldErrorResource.setMessage(message);

        fieldErrors.add(fieldErrorResource);

        errorResource.setFieldErrors(fieldErrors);
        return convertToResponse(errorResource, null, httpStatus);
    }

    // Send Multiple Validation message
    public ResponseEntity<?> sendMultipleValidationMessage(List<String> field, List<String> message, HttpStatus httpStatus){

        /////////////////////////////////////////////////////////
        // Validation

        ErrorResource errorResource = new ErrorResource();
        FieldErrorResource fieldErrorResource = null;
        List<FieldErrorResource> fieldErrors = new ArrayList<>();

        for(int i=0;i<field.size();i++) {
            fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setField(field.get(i));
            fieldErrorResource.setMessage(message.get(i));

            fieldErrors.add(fieldErrorResource);
        }

        if(fieldErrors!=null && !fieldErrors.isEmpty())
            errorResource.setFieldErrors(fieldErrors);

        return convertToResponse(errorResource, null, httpStatus);
    }
}
