package com.trab.trab1sd.repository

import com.trab.trab1sd.domain.MeioTransporte
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MeioTransporteRepository: ReactiveMongoRepository<MeioTransporte, String>