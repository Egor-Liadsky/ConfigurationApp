package com.server.database.configuration

import com.server.features.configuration.ConfigurationResponse
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ConfigurationEntity : Table("configuration") {
    private val rowId = integer("id").autoIncrement()
    private val title = varchar("title", 30)
    private val colorButton = varchar("colorButton", 30)
    private val colorText = varchar("colorText", 30)

    override val primaryKey = PrimaryKey(rowId, name = "id")

    fun insert(configurationDTO: ConfigurationDTO) {
        transaction {
            insert {
                it[title] = configurationDTO.title
                it[colorButton] = configurationDTO.colorButton
                it[colorText] = configurationDTO.colorText
            }
        }
    }

    fun getConfiguration(id: Int): ConfigurationResponse {
        val configurationEntity = transaction { ConfigurationEntity.select { rowId.eq(id) }.single() }
        return ConfigurationResponse(
            id = id,
            title = configurationEntity[title],
            colorButton = configurationEntity[colorButton],
            colorText = configurationEntity[colorText]
        )
    }

    fun getAllConfigurations(): List<ConfigurationResponse> {
        return transaction {
            ConfigurationEntity.selectAll().map {
                ConfigurationResponse(
                    id = it[rowId],
                    title = it[title],
                    colorButton = it[colorButton],
                    colorText = it[colorText]
                )
            }
        }
    }
}