package org.hrv.web.service.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.hrv.web.utils.LoadProperties
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

object DatabaseFactory {

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