package com.darrylbayliss.carelist.data.mappers

import com.darrylbayliss.carelist.data.db.entities.TaskEntity
import com.darrylbayliss.carelist.data.dto.TaskDTO
import com.darrylbayliss.carelist.data.models.*

class TaskMapper {

    fun toTask(taskDTO: TaskDTO): Task {

        val taskType = when (taskDTO.type) {
            GENERAL_TYPE -> General
            HYDRATION_TYPE -> Hydration
            MEDICATION_TYPE -> Medication
            NUTRITION_TYPE -> Nutrition
            else -> General
        }

        return Task(
            taskDTO.id,
            taskDTO.name,
            taskDTO.description,
            taskType
        )
    }

    fun toTask(taskEntity: TaskEntity): Task {

        return Task(
            taskEntity.id,
            taskEntity.name,
            taskEntity.description,
            taskEntity.type
        )
    }

    companion object {
        const val GENERAL_TYPE = "general"
        const val HYDRATION_TYPE = "hydration"
        const val MEDICATION_TYPE = "medication"
        const val NUTRITION_TYPE = "nutrition"
    }
}