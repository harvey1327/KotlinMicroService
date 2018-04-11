package org.hrv.web

import java.util.concurrent.atomic.AtomicInteger

data class User(val name: String, val email: String, val id: Int)

class UserDao {

    private val users = hashMapOf(
            0 to User(name = "Alice", email = "test1@email.com", id = 0),
            1 to User(name = "Bob", email = "test2@email.com", id = 1),
            2 to User(name = "Carol", email = "test3@email.com", id = 2),
            3 to User(name = "Dave", email = "test4@email.com", id = 3)
    )

    fun getAllUsers(): Map<Int, User>? {
        return users
    }

    fun post(name: String, email: String) {
        val id = AtomicInteger(users.size - 1).incrementAndGet()
        users.put(id, User(name = name, email = email, id = id))
    }

    fun getById(id: Int): User? {
        return users[id]
    }

    fun put(id: Int, name: String, email: String) {
        users.put(id, User(name = name, email = email, id = id))
    }

    fun delete(id: Int) {
        users.remove(id)
    }

}