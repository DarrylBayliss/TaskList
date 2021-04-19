package com.darrylbayliss.carelist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.darrylbayliss.carelist.data.models.TaskType

@Entity
data class TaskEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val type: TaskType
)