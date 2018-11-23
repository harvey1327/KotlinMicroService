package org.hrv.web.service.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.hrv.web.domain.AccountTable
import org.hrv.web.domain.ReadAccount
import org.hrv.web.domain.WriteAccount
import org.hrv.web.service.database.DataBaseService.DatabaseFactory.dbQuery
import org.hrv.web.utils.LoadProperties
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class DataBaseService {

    suspend fun getAllAccounts(): List<ReadAccount> = dbQuery { org.hrv.web.domain.AccountTable.selectAll().map { toAccount(it) } }

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

    private object DatabaseFactory {

        private val dispatcher: CoroutineContext

        init {
            dispatcher = Executors.newFixedThreadPool(5).asCoroutineDispatcher()
        }

        fun init(){
            Database.connect(hikari())
        }

        private fun hikari(): HikariDataSource {
            val properties = LoadProperties("hikari.properties")
            val config = HikariConfig(properties)
            return HikariDataSource(config)
        }

        suspend fun <T> dbQuery(block: () -> T): T = withContext(dispatcher){ transaction { block() } }
    }
}