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
import java.io.File
import java.io.FileInputStream

fun main(args: Array<String>){
    val propFile = File("application.conf")
    if(propFile.isFile){
        val stream = FileInputStream(propFile)
        println(stream)
    } else {
        println("Not A file")
    }
    DatabaseFactory.init()
}

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