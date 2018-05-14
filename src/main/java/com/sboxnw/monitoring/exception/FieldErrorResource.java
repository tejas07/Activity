package com.sboxnw.monitoring.exception;

import lombok.Data;

/**
 * Copyright (C) Margo Networks Private Limited - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas.gowda@sugarboxnetworks.com>, March 2018.
 */
@Data
public class FieldErrorResource {
    private String field;
    private String message;
}
