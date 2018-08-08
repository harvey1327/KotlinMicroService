package org.hrv.web.route

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import org.hrv.web.domain.Account
import org.hrv.web.service.AccountService

fun Route.accountRoute(accountService: AccountService){

    route("/account") {

        get("/all"){
            accountService.connection()
            val accounts = accountService.getAllAccounts()
            call.respond(accounts)
        }

        get("/{id}"){
            val id = call.parameters["id"]?.toInt()
            accountService.connection()
            val account = accountService.getAccountById(id)
            call.respond(account)
        }

        post("/create"){
            accountService.connection()
            val account = call.receive<Account>()
            call.respond(accountService.createAccount(account))
        }

        put("/update"){
            accountService.connection()
            val account = call.receive<Account>()
            call.respond(accountService.updateAccount(account))
        }

        delete("/delete/{id}"){
            val id = call.parameters["id"]?.toInt()
            accountService.connection()
            call.respond(accountService.deleteAccount(id))
        }
    }

}