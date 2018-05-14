package com.sboxnw.monitoring.dao;

import com.sboxnw.monitoring.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright (C) Margo Networks Private Limited - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Tejas Gowda <tejas.gowda@sugarboxnetworks.com>, March 2018.
 */
@Transactional
public interface EventsDao extends MongoRepository<Event,String>,PagingAndSortingRepository<Event, String> {
    Page<Event> findOneByEventIdOrderByCreateDateDesc(String eventId, Pageable pageable);

    List<Event> findByEventIdOrderByCreateDateDesc(String eventId);
}
