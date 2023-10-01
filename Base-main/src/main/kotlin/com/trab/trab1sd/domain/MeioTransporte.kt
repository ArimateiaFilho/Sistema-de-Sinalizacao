package com.trab.trab1sd.domain

import org.springframework.data.annotation.Id
import java.util.*

data class MeioTransporte(
        @Id val identificador: String,
        val latitude: Double,
        val longitude: Double,
        val data: Date,
        val idEvento: Int,
        val port: Int,
        val address: String
)