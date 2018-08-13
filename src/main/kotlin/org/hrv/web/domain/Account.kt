package org.hrv.web.domain

import org.jetbrains.exposed.sql.Table

data class Account(val name: String, val id: Int)

object AccountTable : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 50)
}