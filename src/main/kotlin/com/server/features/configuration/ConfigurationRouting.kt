package com.server.features.configuration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.configurationRouting() {
    post("/configuration") {
        val configurationController = ConfigurationController(call)
        configurationController.createConfiguration()
    }

    get("/configuration/{id?}") {
        val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound, "Missing id")
        val configurationController = ConfigurationController(call)
        configurationController.getConfiguration(id.toInt())
    }

    get("/configuration") {
        val configurationController = ConfigurationController(call)
        configurationController.getAllConfigurations()
    }

    delete("/configuration/{id?}") {
        val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.NotFound, "Missing id")
        val configurationController = ConfigurationController(call)
        configurationController.deleteConfiguration(id.toInt())
    }

    put("/configuration/{id?}") {
        val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.NotFound, "Missing id")
        val configurationController = ConfigurationController(call)
        configurationController.updateConfiguration(id.toInt())
    }
}