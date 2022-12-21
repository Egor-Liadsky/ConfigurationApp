package com.server.features.configuration

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationReceive(
    val title: String,
    val colorButton: String,
    val colorText: String
)

@Serializable
data class ConfigurationResponse(
    val id: Int,
    val title: String,
    val colorButton: String,
    val colorText: String
)