package com.activity.monitoring.exception;

import lombok.Data;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
@Data
public class FieldErrorResource {
    private String field;
    private String message;
}
