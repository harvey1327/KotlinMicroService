package org.hrv.web.dao.domainDB

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object Accounts : IntIdTable() {
    val name = varchar("name",50)
}

class AccountDB(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AccountDB>(Accounts)
    var name by Accounts.name
}