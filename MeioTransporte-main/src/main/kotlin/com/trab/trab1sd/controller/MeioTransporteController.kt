package com.trab.trab1sd.controller

import com.trab.trab1sd.worker.Publisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/mt")
class MeioTransporteController(
    private val publisher: Publisher
){

    @GetMapping
    fun pegarMT(): Mono<String> {
        publisher.sendToKafka()
        return Mono.just("Ok!")
    }

}