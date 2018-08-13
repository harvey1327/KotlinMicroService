package org.hrv.web.service

import org.hrv.web.domain.Account
import org.hrv.web.domain.AccountTable
import org.hrv.web.service.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class AccountService {

    suspend fun getAccountById(id: Int): Account? =
            dbQuery { AccountTable.select { AccountTable.id eq id }.mapNotNull { toAccount(it) }.singleOrNull() }

    fun createAccount(account: Account) {

    }

    fun getAllAccounts(): List<Account> {

    }

    fun updateAccount(account: Account) {

    }

    fun deleteAccount(id: Int?) {

    }

    private fun toAccount(row: ResultRow): Account = Account(row[AccountTable.name], row[AccountTable.id])
}