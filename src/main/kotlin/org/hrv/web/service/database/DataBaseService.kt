package org.hrv.web.service.database

import org.hrv.web.domain.AccountTable
import org.hrv.web.domain.ReadAccount
import org.hrv.web.domain.WriteAccount
import org.hrv.web.service.database.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

class DataBaseService {

    suspend fun getAllAccounts(): List<ReadAccount> = dbQuery { AccountTable.selectAll().map { toAccount(it) } }

    suspend fun getAccountById(id: Int): ReadAccount? = dbQuery { AccountTable.select { AccountTable.id eq id }.mapNotNull { toAccount(it) }.singleOrNull() }

    suspend fun createAccount(account: WriteAccount): ReadAccount {
        var key: Int = -1
        dbQuery {
            key = (AccountTable.insert { it[name] = account.name } get AccountTable.id)!!
        }
        return getAccountById(key)!!
    }

    suspend fun updateAccount(account: WriteAccount, id: Int): ReadAccount? {
        dbQuery {
            AccountTable.update({AccountTable.id eq id}) { it[name] = account.name }
        }
        return getAccountById(id)
    }

    suspend fun deleteAccount(id: Int): Boolean = dbQuery { AccountTable.deleteWhere { AccountTable.id eq id } > 0 }

    fun startDataBase() = DatabaseFactory.init()

    private fun toAccount(row: ResultRow): ReadAccount = ReadAccount(row[AccountTable.name], row[AccountTable.id])
}