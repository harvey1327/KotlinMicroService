package org.hrv.web

import com.google.gson.Gson
import org.hrv.web.dao.PlsqlDAO
import org.hrv.web.domain.Account
import spark.Spark.*

fun main(args: Array<String>) {

    val gson = Gson()

    fun jsonToAccount(json: String): Account = gson.fromJson(json, Account::class.java)

    get("/account/all") { _, _ ->
        PlsqlDAO().connection()
        val acc = PlsqlDAO().getAllAccounts()
        gson.toJson(acc)
    }

    post("/account/create") { req, _ ->
        PlsqlDAO().connection()
        PlsqlDAO().createAccount(name = jsonToAccount(req.body()).name)
        "{\"message\" : \"Posted successfully\"}"
    }

    delete("/account/delete") { req, _ ->
        PlsqlDAO().connection()
        PlsqlDAO().deleteAccount(id = jsonToAccount(req.body()).id)
        "{\"message\" : \"Deleted successfully\"}"
    }

    patch("/account/update") { req, _ ->
        PlsqlDAO().connection()
        PlsqlDAO().updateAccount(
                id = jsonToAccount(req.body()).id,
                name = jsonToAccount(req.body()).name
        )
        "{\"message\" : \"Patched successfully\"}"
    }

    notFound { _, _ ->
        "{\"message\":\"Custom 404\"}"
    }
}