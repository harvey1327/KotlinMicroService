package org.hrv.web

import java.util.concurrent.atomic.AtomicInteger

data class User(val name: String, val email: String, val id: Int)

class UserDao {

    private val users = hashMapOf(
            0 to User(name = "Alice", email = "alice@alice.kt", id = 0),
            1 to User(name = "Bob", email = "bob@bob.kt", id = 1),
            2 to User(name = "Carol", email = "carol@carol.kt", id = 2),
            3 to User(name = "Dave", email = "dave@dave.kt", id = 3)
    )

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