package com.activity.monitoring.controller;

import com.activity.monitoring.domain.Notify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-05-12T12:07:58.556Z")
@Api(value = "notificationApi", description = "NotificationApi's")
public interface NotificationApi {
    /* @ApiOperation(value = "post-events details", notes = "post events needs to be added", response = NotificationWrapper.class, tags={ "Notification" })
     @ApiResponses(value = {
             @ApiResponse(code = 200, message = "Success", response = NotificationWrapper.class),
             @ApiResponse(code = 400, message = "Bad request.", response = NotificationWrapper.class),
             @ApiResponse(code = 401, message = "Authorization information is missing or invalid.", response = NotificationWrapper.class),
             @ApiResponse(code = 200, message = "unexpected error", response = NotificationWrapper.class) })
     @RequestMapping(value = "/post-events",
             produces = { "application/json" },
             consumes = { "application/json" },
             method = RequestMethod.POST)
     ResponseEntity<?> postEvents(@ApiParam(value = "Notificatiob object needs to be added" , required = true) @RequestBody @Validated List<NotificationWrapper> notificationWrapperList, BindingResult bindingResult) throws Exception;
 */
    @ApiOperation(value = "get total subscriber details based on the analytics data supplied", notes = "", response = Notify.class, tags = {"Notification"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Notify.class),
            @ApiResponse(code = 400, message = "Bad request.", response = Notify.class),
            @ApiResponse(code = 401, message = "Authorization information is missing or invalid.", response = Notify.class),
            @ApiResponse(code = 200, message = "unexpected error", response = Notify.class)})
    @RequestMapping(value = "/events",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<?> getEventsById(@RequestParam(name = "eventId", required = true) String id, Pageable pageable,
                                    @RequestParam(name = "page", required = false) Integer page,
                                    @RequestParam(name = "count", required = false) Integer count) throws Exception;

}
