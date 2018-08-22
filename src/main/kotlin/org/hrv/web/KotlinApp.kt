package org.hrv.web

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.routing.*
import org.hrv.web.route.accountRoute
import org.hrv.web.service.AccountService
import org.hrv.web.service.DatabaseFactory

fun Application.accountModule(){
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            serializeNulls()
        }
    }
    install(Routing){
        accountRoute(AccountService())
    }

    DatabaseFactory.init()
}