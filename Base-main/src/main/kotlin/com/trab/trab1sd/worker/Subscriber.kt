package com.trab.trab1sd.worker

import com.trab.trab1sd.domain.MeioTransporte
import com.trab.trab1sd.repository.EventBase
import com.trab.trab1sd.repository.MeioTransporteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class Subscriber (
    private val meioTransporteRepository: MeioTransporteRepository,
    private val eventBase: EventBase
){

    private val LOGGER : Logger = LoggerFactory.getLogger(Subscriber::class.java)


    @KafkaListener(
        topics = ["kafka-trabSD"],
        groupId = "kafka-trab1-group"
    )
    fun receivedFromkafka(meioTransporte: String){

        val mt = meioTransporte.split("-")
        val objMT = MeioTransporte(mt[0],mt[1].toDouble(),mt[2].toDouble(), Date(mt[3].toLong()),Integer.parseInt(mt[4]),Integer.parseInt(mt[5]),mt[6])

        meioTransporteRepository.save(objMT).subscribe()

        eventBase.processEvent(objMT)

        LOGGER.info(meioTransporte)
    }

    private fun tempolimite(date: Long) = Date().time-date >= 60000


    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    private fun timeout() {
        meioTransporteRepository.findAll()
            .map { meioTransporte ->
                if (tempolimite(meioTransporte.data.time)) {
                    LOGGER.info("Deu problema no meio de transporte com id: {}",meioTransporte.identificador)
                    eventBase.processEventTimeout(meioTransporte)
                }
            }.subscribe()
    }

}

