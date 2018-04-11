package org.hrv.web

import com.google.gson.Gson
import spark.Spark.*

fun main(args: Array<String>) {

    val userDao = UserDao()
    val gson = Gson()

    get("/user/id/:id") { req, _ ->
        val user = userDao.getById(req.params("id").toInt())
        gson.toJson(user)
    }

    get("/user/all") { _, _ ->
        val users = userDao.getAllUsers()
        gson.toJson(users)
    }
    post("/user/create") { req, res ->
        userDao.post(
                name = req.queryParams("name"),
                email = req.queryParams("email"))
        res.status(201)
    }

    patch("/user/update/:id") { req, res ->
        userDao.put(
                id = req.params("id").toInt(),
                name = req.queryParams("name"),
                email = req.queryParams("email")
        )
        res.status(200)
    }

    delete("/user/delete/:id") { req, res ->
        userDao.delete(req.params("id").toInt())
        res.status(200)
    }
}
