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
        return HikariDataSource(config)
    }

    private val dispatcher: CoroutineContext

    init {
        dispatcher = newFixedThreadPoolContext(5, "database-pool")
    }

    suspend fun <T> dbQuery(block: () -> T): T = withContext(dispatcher) { transaction { block() } }
}