package org.hrv.web.dao

import org.hrv.web.dao.domainDB.AccountDB
import org.hrv.web.domain.Account
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionScope

class PlsqlDAO {

    fun connection() = Database.connect(
            url = "jdbc:postgresql://localhost:5432/sparkservice",
            driver = "org.postgresql.Driver",
            user = "sparkuser",
            password = "password"
    )

//    fun insertAccount() {
//        transaction {
//            logger.addLogger(StdOutSqlLogger)
//
//            create (Accounts)
//            AccountDB.new {
//                name = "AccountOne"
//            }
//            AccountDB.new {
//                name = "AccountTwo"
//            }
//
//        }
//    }

    fun createAccount(name: String) {
        transaction {
            logger.addLogger(StdOutSqlLogger)

            AccountDB.new {
                this.name = name
            }
        }
    }

    fun getAllAccounts(): List<Account>? {
        var accounts:List<Account>? = null
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