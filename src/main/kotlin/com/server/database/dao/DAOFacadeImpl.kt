package com.server.database.dao

import com.server.database.dao.DatabaseFactory.dbQuery
import com.server.database.models.ConfigurationModel
import com.server.database.models.ConfigurationModel.colorButton
import com.server.database.models.ConfigurationModel.colorText
import com.server.database.models.ConfigurationModel.rowId
import com.server.database.models.ConfigurationModel.title
import com.server.features.configuration.ConfigurationReceive
import com.server.features.configuration.ConfigurationResponse
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {
    override suspend fun createConfiguration(configurationReceive: ConfigurationReceive) = dbQuery {
        ConfigurationModel.insert {
            it[title] = configurationReceive.title
            it[colorButton] = configurationReceive.colorButton
            it[colorText] = configurationReceive.colorText
        }
        return@dbQuery
    }

    override suspend fun getConfiguration(id: Int): ConfigurationResponse = dbQuery {
        val configurationEntity = ConfigurationModel.select { ConfigurationModel.rowId.eq(id) }.single()
        return@dbQuery ConfigurationResponse(
            id = id,
            title = configurationEntity[title],
            colorButton = configurationEntity[colorButton],
            colorText = configurationEntity[colorText]
        )
    }

    override suspend fun getAllConfigurations(): List<ConfigurationResponse> = dbQuery {
        return@dbQuery ConfigurationModel.selectAll().map {
            ConfigurationResponse(
                id = it[rowId],
                title = it[title],
                colorButton = it[colorButton],
                colorText = it[colorText]
            )
        }
    }
}

val dao: DAOFacade = DAOFacadeImpl()