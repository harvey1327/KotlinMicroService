package org.hrv.web.domain

import org.jetbrains.exposed.sql.Table

data class ReadAccount(val name: String, val id: Int)

data class WriteAccount(val name: String)

object AccountTable : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val name = varchar("name", 50)
}