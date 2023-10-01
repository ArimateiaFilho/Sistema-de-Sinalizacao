package com.trab.trab1sd.repository

import com.trab.trab1sd.domain.MeioTransporte

interface IEventsProcessor {

    fun processEvent(meioTransporte: MeioTransporte)

    fun processEventTimeout(meioTransporte: MeioTransporte)
}