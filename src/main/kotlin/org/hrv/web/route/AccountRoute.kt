package org.hrv.web.route

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.hrv.web.domain.Account
import org.hrv.web.service.AccountService

fun Route.accountRoute(accountService: AccountService){

    route("/account") {

        val notFound = "{ 'message' : 'Not Found' }"

        get("/all"){
            val accounts = accountService.getAllAccounts()
            call.respond(accounts)
        }

        get("/{id}"){
            val id = call.parameters["id"]!!.toInt()
            val account = accountService.getAccountById(id)
            if(account == null){
                call.respond(HttpStatusCode.NotFound, notFound)
            } else {
                call.respond(account)
            }
        }

        post("/create"){
            val account = call.receive<Account>()
            call.respond(accountService.createAccount(account))
        }

        put("/update"){
            val account = call.receive<Account>()
            call.respond(accountService.updateAccount(account))
        }

        delete("/delete/{id}"){
            val id = call.parameters["id"]?.toInt()
            call.respond(accountService.deleteAccount(id))
        }
    }

}