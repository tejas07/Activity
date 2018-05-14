package com.activity.monitoring.dao;

import com.activity.monitoring.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (C) Tejas Gowda Activity Sample - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas7111991@gmail.com>, March 2018.
 */
@Transactional
public interface EventsDao extends MongoRepository<Event,String>,PagingAndSortingRepository<Event, String> {
}
