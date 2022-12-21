package com.server.database.configuration

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDTO(
    val id: Int,
    val title: String,
    val colorButton: String,
    val colorText: String
)