package com.activity.monitoring.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
@Data
@Document(collection = "tEvent")
public class Event {
    @Id
    @JsonIgnore
    String id;
    @JsonProperty("eventId")
    String eventId;
    @JsonProperty("noun")
    String noun;
    @JsonProperty("verb")
    String verb;
    @JsonProperty("timeStamp")
    Long timeStamp;
    @JsonProperty("data")
    private Object data;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     private java.util.Date createDate;
}
