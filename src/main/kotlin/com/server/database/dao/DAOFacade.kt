package com.server.database.dao

import com.server.features.configuration.ConfigurationReceive
import com.server.features.configuration.ConfigurationResponse

interface DAOFacade {

    suspend fun createConfiguration(configurationReceive: ConfigurationReceive)

    suspend fun getConfiguration(id: Int): ConfigurationResponse

    suspend fun getAllConfigurations(): List<ConfigurationResponse>

    suspend fun deleteConfiguration(id: Int): Int

    suspend fun updateConfiguration(id: Int, configurationResponse: ConfigurationResponse): ConfigurationResponse
}