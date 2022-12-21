package com.server.plugins

import com.server.features.configuration.configurationRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
//        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml"){
//            version = "1.0.0"
//        }
        configurationRouting()
    }
}
