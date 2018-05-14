package com.activity.monitoring.exception;

import lombok.Data;
import org.springframework.validation.Errors;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
@Data
@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super();
        this.errors = errors;
    }
}
