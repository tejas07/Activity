package com.sboxnw.monitoring.controller;

import com.sboxnw.monitoring.exception.ErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (C) Margo Networks Private Limited - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Kunal Utage <kunal.utage@catseyetech.in>, November 2017.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    // Method for converting output to response
    public ResponseEntity convertToResponse(Object object, HttpStatus httpStatus){

        logger.info("httpStatus: " + httpStatus);

        Map<String,Integer> meta=new HashMap<>();
        meta.put("code",httpStatus.value());
        LinkedHashMap<String,Object> response=new LinkedHashMap<>();
        response.put("meta",meta);
        response.put("data",object);
        return ResponseEntity.ok(response);
    }
    // Method for converting output to response
    public ResponseEntity convertToResponse(Object object, int size, HttpStatus httpStatus){

        Map<String,String> meta=new HashMap<>();
        meta.put("code",httpStatus.toString());
        LinkedHashMap<String,Object> response=new LinkedHashMap<>();
        response.put("meta",meta);
        response.put("data",object);
        Map<String,Integer> count=new HashMap<>();
        count.put("count",size);
        response.put("pagination",count);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity convertToResponse(Object object, HttpStatus httpStatus,int page,int count){

        Map<String,String> meta=new HashMap<>();
        meta.put("code",httpStatus.toString());

        LinkedHashMap<String,Integer> pagination=new LinkedHashMap<>();
        pagination.put("page",page);
        pagination.put("count",count);

        LinkedHashMap<String,Object> response=new LinkedHashMap<>();
        response.put("meta",meta);
        response.put("data",object);
        response.put("pagination",pagination);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity convertToResponse(HttpStatus httpStatus){
        Map<String,String> meta=new HashMap<>();
        meta.put("code",httpStatus.toString());

        LinkedHashMap<String,Object> response=new LinkedHashMap<>();
        response.put("meta",meta);

        Map object = new HashMap();
        response.put("data",object);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity convertToResponse(Object object, HttpStatus httpStatus
            ,int count,int currentPage,Long total,int lastPage){
        Map<String,String> meta=new HashMap<>();
        meta.put("code",httpStatus.toString());

        LinkedHashMap pagination=new LinkedHashMap<>();
        pagination.put("count",count);
        pagination.put("currentPage",currentPage);
        pagination.put("total",total);
        pagination.put("lastPage",lastPage);


        LinkedHashMap<String,Object> response=new LinkedHashMap<>();
        response.put("meta",meta);
        response.put("data",object);
        response.put("pagination",pagination);
        return ResponseEntity.ok(response);
    }

    // With validation error response
    public ResponseEntity convertToResponse(ErrorResource errorResource, Map dataMap, HttpStatus httpStatus){

        LinkedHashMap<String,Object> response = null;
        Map meta = new HashMap();

        if(dataMap != null) {
            meta.put("code", httpStatus.toString());

            response = new LinkedHashMap<>();
            response.put("meta",meta);
            response.put("data",dataMap);
        }else{
            meta.put("code", httpStatus.value());
            meta.put("errors", errorResource.getFieldErrors());

            response=new LinkedHashMap<>();
            response.put("meta",meta);
        }

        logger.info("response: {}", response);
        return ResponseEntity.ok(response);
    }
}
