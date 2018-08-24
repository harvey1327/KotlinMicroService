package org.hrv.web.service

import org.hrv.web.domain.Account
import org.hrv.web.domain.AccountTable
import org.hrv.web.service.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*

class AccountService {

    suspend fun getAllAccounts(): List<Account> = dbQuery { AccountTable.selectAll().map { toAccount(it) } }

    suspend fun getAccountById(id: Int): Account? = dbQuery { AccountTable.select { AccountTable.id eq id }.mapNotNull { toAccount(it) }.singleOrNull() }

    suspend fun createAccount(account: Account): Account {
        var key: Int = -1
        dbQuery {
            key = (AccountTable.insert { it[name] = account.name } get AccountTable.id)!!
        }
        return getAccountById(key)!!
    }

    suspend fun updateAccount(account: Account): Account? {
        dbQuery {
            AccountTable.update({AccountTable.id eq account.id}) { it[name] = account.name }
        }
        return getAccountById(account.id)
    }

    suspend fun deleteAccount(id: Int): Boolean = dbQuery { AccountTable.deleteWhere { AccountTable.id eq id } > 0 }

    private fun toAccount(row: ResultRow): Account = Account(row[AccountTable.name], row[AccountTable.id])
}