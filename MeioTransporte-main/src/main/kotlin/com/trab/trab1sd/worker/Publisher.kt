package com.trab.trab1sd.worker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class Publisher(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val LOGGER : Logger = LoggerFactory.getLogger(Publisher::class.java)

    @Value("\${server.port}")
    private lateinit var port: String

    @Value("\${server.address}")
    private lateinit var address: String

    @Value("\${nomemt}")
    private lateinit var nome: String

    private fun publishMT(meioTransporte: String){
        kafkaTemplate.send("kafka-trabSD",meioTransporte)
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    fun sendToKafka(){
        val lat = (0..100).random()
        val long = (0..100).random()
        val identificador = (0..1).random()

        LOGGER.info("mandei")

        publishMT(nome+"-"+lat+"-"+long+"-"+Date().time+"-"+identificador+"-"+port+"-"+address)
    }

}