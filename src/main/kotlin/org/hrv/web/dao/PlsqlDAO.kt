package org.hrv.web.dao

import org.hrv.web.domain.Account
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

class PlsqlDAO {

    object Accounts : IntIdTable() {
        val name = varchar("name", 50)
    }

    class AccountDB(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<AccountDB>(Accounts)
        var name by Accounts.name
    }

    fun connection() = Database.connect(
            url = "jdbc:postgresql://sparkdb:5432/sparkservice",
            driver = "org.postgresql.Driver",
            user = "sparkuser",
            password = "password"
    )

    fun createAccount(name: String) {
        transaction {
            logger.addLogger(StdOutSqlLogger)

            AccountDB.new {
                this.name = name
            }
        }
    }

    fun getAccountById(id: Int): Account? {
        var account:Account? = null
        transaction {
            logger.addLogger(StdOutSqlLogger)
            var name = AccountDB.findById(id)?.name
            if(name != null){
                account = Account(name = name, id = id)
            }
        }
        return account
    }

    fun getAllAccounts(): List<Account> {
        var accounts:List<Account> = ArrayList()
        transaction {
            logger.addLogger(StdOutSqlLogger)
            val accountsDBList = AccountDB.all().asSequence().toList()
            accounts = accountsDBList.map { accountDB ->
                Account(name = accountDB.name, id = accountDB.id.value)
            }.asSequence().toList()
        }
        return accounts
    }

    fun updateAccount(id: Int, name : String) {
        transaction {
            logger.addLogger(StdOutSqlLogger)
            val accountDB = AccountDB.findById(id)
            accountDB?.name = name
        }
    }

    fun deleteAccount(id: Int) {
        transaction {
            logger.addLogger(StdOutSqlLogger)
            AccountDB.findById(id)?.delete()
        }
    }
}