package com.server.database.dao

import com.server.database.dao.DatabaseFactory.dbQuery
import com.server.database.models.ConfigurationModel
import com.server.database.models.ConfigurationModel.colorButton
import com.server.database.models.ConfigurationModel.colorText
import com.server.database.models.ConfigurationModel.rowId
import com.server.database.models.ConfigurationModel.title
import com.server.features.configuration.ConfigurationReceive
import com.server.features.configuration.ConfigurationResponse
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

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
        val configurationModel = ConfigurationModel.select { rowId.eq(id) }.single()
        return@dbQuery ConfigurationResponse(
            id = id,
            title = configurationModel[title],
            colorButton = configurationModel[colorButton],
            colorText = configurationModel[colorText]
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

    override suspend fun deleteConfiguration(id: Int): Int = dbQuery {
        ConfigurationModel.deleteWhere { rowId eq id }
        return@dbQuery id
    }

    override suspend fun updateConfiguration(id: Int, configurationResponse: ConfigurationResponse): ConfigurationResponse = dbQuery {
        ConfigurationModel.update({ rowId eq id }) {
            it[rowId] = configurationResponse.id
            it[title] = configurationResponse.title
            it[colorButton] = configurationResponse.colorButton
            it[colorText] = configurationResponse.colorText
        }
        return@dbQuery ConfigurationResponse(
            id = id,
            title = configurationResponse.title,
            colorButton = configurationResponse.colorButton,
            colorText = configurationResponse.colorText
        )
    }
}

val dao: DAOFacade = DAOFacadeImpl()