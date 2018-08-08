package org.hrv.web

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import org.hrv.web.route.accountRoute
import org.hrv.web.service.AccountService

fun main(args: Array<String>){
    embeddedServer(Jetty, port = 8080, watchPaths = listOf("KotlinApp"), module = Application::module).start()
}

fun Application.module(){
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
}