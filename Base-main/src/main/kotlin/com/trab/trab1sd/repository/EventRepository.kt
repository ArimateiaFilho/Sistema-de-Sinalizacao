package com.trab.trab1sd.repository

import com.trab.trab1sd.domain.Event
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: ReactiveMongoRepository<Event, String>