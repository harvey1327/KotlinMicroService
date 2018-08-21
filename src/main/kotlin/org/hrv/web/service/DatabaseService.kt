package org.hrv.web.service

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.coroutines.experimental.CoroutineContext

object DatabaseFactory {

    fun init(){
        Database.connect(hikari())
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig("hikari.properties")
//        config.driverClassName = "org.postgresql.Driver"
//        config.jdbcUrl = "jdbc:postgresql://ktordb:5432/ktorservice"
//        config.maximumPoolSize = 3
//        config.isAutoCommit = true
//        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
//        config.username = "ktoruser"
//        config.password = "password"
        config.validate()
        return HikariDataSource(config)
    }

    private val dispatcher: CoroutineContext

    init {
        dispatcher = newFixedThreadPoolContext(5, "database-pool")
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(dispatcher) { transaction { block() } }
}