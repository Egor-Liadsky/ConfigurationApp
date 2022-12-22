package com.server.features.configuration

import com.server.database.dao.dao
import com.server.database.configuration.ConfigurationDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class ConfigurationController(private val call: ApplicationCall) {

    suspend fun createConfiguration() {
        val configurationReceive = call.receive<ConfigurationReceive>()

       dao.createConfiguration(
            ConfigurationReceive(
                title = configurationReceive.title,
                colorButton = configurationReceive.colorButton,
                colorText = configurationReceive.colorText
            )
        )
        call.respond(HttpStatusCode.Created, "Configuration create")
    }

    suspend fun getConfiguration(id: Int) {
        call.respond(HttpStatusCode.OK, dao.getConfiguration(id))
    }

    suspend fun getAllConfigurations() {
        call.respond(HttpStatusCode.OK, dao.getAllConfigurations())
    }
}