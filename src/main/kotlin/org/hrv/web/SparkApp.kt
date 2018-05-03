package org.hrv.web

import com.google.gson.Gson
import org.hrv.web.dao.PlsqlDAO
import spark.Spark.*

fun main(args: Array<String>) {

    val gson = Gson()

//    get("/testpost") {_, res ->
//        PlsqlDAO().connection()
//        PlsqlDAO().insertAccount()
//        res.status(200)
//    }

    get("/account/all") {_, res ->
        PlsqlDAO().connection()
        val acc = PlsqlDAO().getAllAccounts()
        gson.toJson(acc)
    }

    post("/account/create") { req, res ->
        PlsqlDAO().connection()
        PlsqlDAO().createAccount(
                name = req.queryParams("name")
        )
        res.status(201)
    }

    delete("/account/delete/:id") { req, res ->
        PlsqlDAO().connection()
        PlsqlDAO().deleteAccount(req.params("id").toInt())
        res.status(201)
    }

    patch("/account/update/:id") { req, res ->
        PlsqlDAO().connection()
        PlsqlDAO().updateAccount(
                id = req.params("id").toInt(),
                name = req.params("name")
        )
        res.status(201)
    }

//    get("/user/id/:id") { req, _ ->
//        val user = userDao.getById(req.params("id").toInt())
//        if (user !=null) gson.toJson(user) else "{\"message\":\"User does not exist\"}"
//    }

    notFound { _, _ ->
        "{\"message\":\"Custom 404\"}"
    }
}