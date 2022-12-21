package com.server.database.dao

import com.server.database.dao.DatabaseFactory.dbQuery
import com.server.database.configuration.ConfigurationDTO
import com.server.database.configuration.ConfigurationEntity
import com.server.database.configuration.ConfigurationEntity.colorButton
import com.server.database.configuration.ConfigurationEntity.colorText
import com.server.database.configuration.ConfigurationEntity.rowId
import com.server.database.configuration.ConfigurationEntity.title
import com.server.features.configuration.ConfigurationResponse
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class DAOFacadeImpl : DAOFacade {
    override suspend fun createConfiguration(configurationDTO: ConfigurationDTO) = dbQuery {
        ConfigurationEntity.insert {
            it[title] = configurationDTO.title
            it[colorButton] = configurationDTO.colorButton
            it[colorText] = configurationDTO.colorText
        }
        return@dbQuery
    }

    override suspend fun getConfiguration(id: Int): ConfigurationResponse = dbQuery {
        val configurationEntity = ConfigurationEntity.select { rowId.eq(id) }.single()
        return@dbQuery ConfigurationResponse(
            id = id,
            title = configurationEntity[title],
            colorButton = configurationEntity[colorButton],
            colorText = configurationEntity[colorText]
        )
    }

    override suspend fun getAllConfigurations(): List<ConfigurationResponse>  = dbQuery {
        return@dbQuery ConfigurationEntity.selectAll().map {
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