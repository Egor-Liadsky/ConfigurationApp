package com.server.database.models

import org.jetbrains.exposed.sql.Table

object ConfigurationModel : Table("configuration") {
    val rowId = integer("id").autoIncrement()
    val title = varchar("title", 30)
    val colorButton = varchar("colorButton", 30)
    val colorText = varchar("colorText", 30)

    override val primaryKey = PrimaryKey(rowId, name = "id")
}