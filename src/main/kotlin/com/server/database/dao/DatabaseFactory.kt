package com.server.database.dao

import com.server.database.configuration.ConfigurationEntity
import com.server.utils.Constants
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val driverClass = "org.postgresql.Driver"
        val jdbcUrls = "jdbc:postgresql://localhost:5432/${Constants.databaseName}"
        val database = Database.connect(jdbcUrls, driverClass, user = Constants.userDb, password = Constants.passwordDb)

        transaction(database) {
            SchemaUtils.create(ConfigurationEntity)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}