package com.darrylbayliss.carelist.data.models

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val type: TaskType
)
