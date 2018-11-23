package org.hrv.web

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.request.path
import io.ktor.routing.*
import org.hrv.web.route.accountRoute
import org.hrv.web.service.AccountService
import org.hrv.web.service.database.DataBaseService
import org.slf4j.event.Level

fun Application.accountModule(){
    install(DefaultHeaders)
    install(CallLogging) {
        level = Level.INFO
        filter{it.request.path().startsWith("/account")}
    }
    install(ContentNegotiation) {
        gson {
            serializeNulls()
        }
    }
    install(Routing){
        accountRoute(AccountService())
    }

    DataBaseService().startDataBase()
}