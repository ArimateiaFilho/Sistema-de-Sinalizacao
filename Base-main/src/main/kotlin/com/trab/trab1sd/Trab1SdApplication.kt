package com.trab.trab1sd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class Trab1SdApplication

fun main(args: Array<String>) {
    runApplication<Trab1SdApplication>(*args)
}
