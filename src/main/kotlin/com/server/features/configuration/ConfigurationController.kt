package com.server.features.configuration

import com.server.database.configuration.ConfigurationDTO
import com.server.database.configuration.ConfigurationEntity
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ConfigurationController(private val call: ApplicationCall) {

    suspend fun createConfiguration() {
        val configurationReceive = call.receive<ConfigurationReceive>()

        ConfigurationEntity.insert(
            ConfigurationDTO(
                id = 0,
                title = configurationReceive.title,
                colorButton = configurationReceive.colorButton,
                colorText = configurationReceive.colorText
            )
        )

        call.respond(HttpStatusCode.Created, "Configuration create")
    }

    suspend fun getConfiguration(id: Int) {
        call.respond(HttpStatusCode.OK, ConfigurationEntity.getConfiguration(id))
    }

    suspend fun getAllConfigurations() {
        call.respond(HttpStatusCode.OK, ConfigurationEntity.getAllConfigurations())
    }
}