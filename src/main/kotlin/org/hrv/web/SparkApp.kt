package org.hrv.web

import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import org.hrv.web.dao.PlsqlDAO
import org.hrv.web.domain.Account

fun main(args: Array<String>) {

    val gson = Gson()

    fun jsonToAccount(json: String): Account = gson.fromJson(json, Account::class.java)

    val server = embeddedServer(Jetty, port = 8080){
        routing {
            get("/account/all"){
                PlsqlDAO().connection()
                val acc = PlsqlDAO().getAllAccounts()
                call.respond(acc)
            }
        }
    }

    server.start(wait=true)

//    get("/account/all") { _, _ ->
//        PlsqlDAO().connection()
//        val acc = PlsqlDAO().getAllAccounts()
//        gson.toJson(acc)
//    }
//
//    get("/account/:id") {req, _ ->
//        val id = req.params("id").toInt()
//        PlsqlDAO().connection()
//        val acc = PlsqlDAO().getAccountById(id)
//        gson.toJson(acc)
//    }
//
//    post("/account/create") { req, _ ->
//        PlsqlDAO().connection()
//        PlsqlDAO().createAccount(name = jsonToAccount(req.body()).name)
//        "{\"message\" : \"Posted successfully\"}"
//    }
//
//    delete("/account/delete") { req, _ ->
//        PlsqlDAO().connection()
//        PlsqlDAO().deleteAccount(id = jsonToAccount(req.body()).id)
//        "{\"message\" : \"Deleted successfully ${jsonToAccount(req.body()).id}\"}"
//    }
//
//    patch("/account/update") { req, _ ->
//        PlsqlDAO().connection()
//        val account: Account = jsonToAccount(req.body())
//        PlsqlDAO().updateAccount(
//                id = account.id,
//                name = account.name
//        )
//        "{\"message\" : \"Patched successfully\"}"
//    }
//
//    notFound { _, _ ->
//        "{\"message\":\"Custom 404\"}"
//    }
}