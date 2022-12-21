package com.server.database.dao

import com.server.database.configuration.ConfigurationDTO
import com.server.features.configuration.ConfigurationResponse
import io.ktor.serialization.*

interface DAOFacade {

    suspend fun createConfiguration(configurationDTO: ConfigurationDTO)

    suspend fun getConfiguration(id: Int): ConfigurationResponse

    suspend fun getAllConfigurations(): List<ConfigurationResponse>
}