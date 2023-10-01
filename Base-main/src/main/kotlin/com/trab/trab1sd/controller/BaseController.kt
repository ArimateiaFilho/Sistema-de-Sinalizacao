package com.trab.trab1sd.controller

import com.trab.trab1sd.domain.MeioTransporte
import com.trab.trab1sd.repository.MeioTransporteRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/base")
class BaseController(
        private val meioTransporteRepository: MeioTransporteRepository
) {

    @PostMapping
    fun addBase(@RequestBody meioTransporte: MeioTransporte): Mono<MeioTransporte> {
        return meioTransporteRepository.save(meioTransporte)
    }

    @PutMapping
    fun updateBase(@RequestBody meioTransporte: MeioTransporte): Mono<MeioTransporte> {
        return meioTransporteRepository.save(meioTransporte)
    }

    @DeleteMapping("/{_id}")
    fun deleteBase(@PathVariable _id: String): Mono<Void> {
        return meioTransporteRepository.deleteById(_id)
    }

    @GetMapping
    fun getBases(): Flux<MeioTransporte> {
        return meioTransporteRepository.findAll()
    }

    @GetMapping("/{_id}")
    fun getBaseById(@PathVariable _id: String): Mono<MeioTransporte> {
        return meioTransporteRepository.findById(_id)
            .switchIfEmpty(Mono.error(ClassNotFoundException("Base não encontrada")))
    }
}