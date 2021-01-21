package com.sonukg.roomexample1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
class Todo(
    @field:PrimaryKey
    val id : String,
    val name : String
)