package com.trab.trab1sd.repository

import com.trab.trab1sd.domain.Event
import com.trab.trab1sd.domain.MeioTransporte
import com.trab.trab1sd.domain.enum.EventType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

@Component
class EventBase(
        private val eventRepository: EventRepository
) : IEventsProcessor {

    private val log = LoggerFactory.getLogger(EventBase::class.java)

    override fun processEvent(meioTransporte: MeioTransporte) {
        if (meioTransporte.idEvento == 0){
            registerEvent(EventType.SUCESSO, meioTransporte.identificador)
        }else{
            registerEvent(EventType.ALERTA, meioTransporte.identificador)
        }
    }

    private fun registerEvent(eventType: EventType, id: String): Mono<Event> {
        val newEvent = Event(eventType = eventType, `when` = Date(), meioTransporte = id)
        log.info("Novo evento de alerta {}", newEvent)
        return eventRepository.save(newEvent)
    }

    override fun processEventTimeout(meioTransporte: MeioTransporte) {
        val url = "http://"+meioTransporte.address+":"+meioTransporte.port+"/mt"

        val conn: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

        conn.setRequestMethod("GET")
        conn.setRequestProperty("Accept", "application/json")


        try {
            if (conn.getResponseCode() !== 200) {
                log.info("Erro " + conn.getResponseCode().toString() + " ao obter dados da URL " + url)
                log.info("Meio de Transporte com id: " + meioTransporte.identificador)
                registerEvent(EventType.OFFLINE, meioTransporte.identificador)
            } else {
                log.info(conn.responseMessage)
                log.info("Conexão feita com sucesso")
                registerEvent(EventType.SUCESSO, meioTransporte.identificador)
            }
        }catch (e: Exception){
            log.info("Erro ao obter dados da URL " + url)
            log.info("Meio de Transporte com id: " + meioTransporte.identificador)
            registerEvent(EventType.OFFLINE, meioTransporte.identificador)
        }

    }

}