package com.activity.monitoring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
@Data
public class NotificationWrapper {
    @JsonProperty("eventList")
    private List<Event> eventList;
}
