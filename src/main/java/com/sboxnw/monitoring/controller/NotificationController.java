package com.sboxnw.monitoring.controller;

import com.sboxnw.monitoring.dao.EventsDao;
import com.sboxnw.monitoring.domain.Event;
import com.sboxnw.monitoring.domain.Notify;
import com.sboxnw.monitoring.exception.ValidationMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C) Margo Networks Private Limited - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas.gowda@sugarboxnetworks.com>, March 2018.
 */
@RestController
public class NotificationController extends BaseController implements NotificationApi {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);
    @Autowired
    private EventsDao eventsDao;

    /*@Override
    public ResponseEntity<?> postEvents(@RequestBody @Validated List<NotificationWrapper> notificationWrapperList, BindingResult bindingResult) throws Exception {
        try {
            logger.info("inside postEvents methods");

            if (bindingResult.hasErrors()) {
                return convertToResponse(bindingResult, HttpStatus.BAD_REQUEST);
            }
            List<NotificationWrapper> notificationWrapperList1 = new ArrayList<>();
            for (NotificationWrapper notificationWrapper : notificationWrapperList) {
                NotificationWrapper notificationWrapper1 = new NotificationWrapper();
                List<Event> eventList = new ArrayList<>();
                logger.info("NotificationWrapper " + notificationWrapper);
                for (Event event : notificationWrapper.getEventList()) {
                    logger.info("Event List " + notificationWrapper.getEventList());
                    Event event1 = new Event();
                    event1.setEventId(event.getEventId());
                    event1.setNoun(event.getNoun());
                    event1.setVerb(event.getVerb());
                    event1.setTimeStamp(event.getTimeStamp());
                    event1.setData(event.getData());
                    event1 = eventsDao.save(event1);
                    eventList.add(event1);
                    logger.info("Event saved " + event1);
                }
                notificationWrapper.setEventList(eventList);
            }
            return convertToResponse(notificationWrapperList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception");
            return convertToResponse(e, HttpStatus.BAD_GATEWAY);
        }
    }*/

    @Override
    public ResponseEntity<?> getEventsById(@RequestParam(name = "eventId") String eventId,
                                           Pageable pageable,
                                           @RequestParam(name = "page", required = false) Integer page,
                                           @RequestParam(name = "count", required = false) Integer count) {
        List<Notify> notifyList = new ArrayList<>();
        ValidationMsg validationMsg = new ValidationMsg();
        try {
            logger.info("getEventsById() : eventId="+eventId);
            Page<Event> event;
            event = eventsDao.findOneByEventIdOrderByCreateDateDesc(eventId, pageable);
            if (event.getTotalElements() == 0)
                return validationMsg.sendValidationMessage("EventId", "Invalid", HttpStatus.NO_CONTENT);
            if (page != null && page != 0) {
                notifyList = sortDateWise(event, notifyList, null);
                return convertToResponse(notifyList, HttpStatus.OK, event.getNumber() + 1, event.getNumberOfElements());
            } else {
                notifyList = sortDateWise(null, notifyList, eventsDao.findByEventIdOrderByCreateDateDesc(eventId));
                return convertToResponse(notifyList, HttpStatus.OK);
            }
        } catch (Exception e) {
            if(e instanceof IndexOutOfBoundsException)
                return convertToResponse(sortDateWise(null, notifyList, eventsDao.findByEventIdOrderByCreateDateDesc(eventId)), HttpStatus.OK);
            logger.error("Exception");
            return validationMsg.sendValidationMessage("Page", "Invalid", HttpStatus.NO_CONTENT);
        }
    }

    private List<Notify> sortDateWise(Page<Event> event, List<Notify> notifyList, List<Event> eventList) {
        logger.info("sortDateWise() ");
        if (eventList == null)
            eventList = event.getContent();
        Notify notify = null;
        Date date = eventList.get(0).getCreateDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        List<Event> eventList1 = null;
        for (Event event1 : eventList) {
            String initialDate = simpleDateFormat.format(event1.getCreateDate());
            String comparingDate = simpleDateFormat.format(date);
            if (initialDate.equals(comparingDate)) {
                date = event1.getCreateDate();
                if (notify == null) {
                    notify = new Notify();
                    eventList1 = new ArrayList<>();
                    eventList1.add(event1);
                    notify.setEvents(eventList1);
                    notify.setDate(simpleDateFormat.format(event1.getCreateDate()));
                    notifyList.add(notify);
                } else if (notify.getDate().compareTo(simpleDateFormat.format(event1.getCreateDate())) == 0) {
                    eventList1.add(event1);
                    notify.setEvents(eventList1);
                }
            } else {
                notify = new Notify();
                date = event1.getCreateDate();
                eventList1 = new ArrayList<>();
                eventList1.add(event1);
                notify.setEvents(eventList1);
                notify.setDate(simpleDateFormat.format(event1.getCreateDate()));
                notifyList.add(notify);
            }
        }
        logger.info("eventList1 size " + eventList1.size());
        return notifyList;
    }

}