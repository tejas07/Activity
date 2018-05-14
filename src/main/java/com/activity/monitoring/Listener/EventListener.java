package com.activity.monitoring.Listener;

import com.activity.monitoring.domain.Event;
import com.activity.monitoring.domain.NotificationWrapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.activity.monitoring.dao.EventsDao;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Date;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
public class EventListener {

    private static final String topic = "${monitoring.event.add}";

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(EventListener.class);
    @Autowired
    private EventsDao eventsDao;

    @KafkaListener(topics =topic)
    public void events(String events) {
        try {
            logger.info("inside events() " + events);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            NotificationWrapper notificationWrapper;
            notificationWrapper = objectMapper.readValue(events, NotificationWrapper.class);
            for (Event event : notificationWrapper.getEventList()) {
                event.setCreateDate(new Date(event.getTimeStamp()));
                event = eventsDao.save(event);
                System.out.println("Event saved " + event.getEventId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(""+e);
        }
    }
}
