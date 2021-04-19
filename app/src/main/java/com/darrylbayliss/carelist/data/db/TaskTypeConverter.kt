package com.darrylbayliss.carelist.data.db

import androidx.room.TypeConverter
import com.darrylbayliss.carelist.data.models.*

class TaskTypeConverter {

    @TypeConverter
    fun fromString(taskType: String): TaskType {

        return when(taskType) {
            GENERAL_TASK_TYPE -> General
            NUTRITION_TASK_TYPE -> Nutrition
            HYDRATION_TASK_TYPE -> Hydration
            MEDICATION_TASK_TYPE -> Medication
            else -> General
        }
    }

    @TypeConverter
    fun taskTypeToString(taskType: TaskType): String {

        return when(taskType) {
            is General -> GENERAL_TASK_TYPE
            is Nutrition -> NUTRITION_TASK_TYPE
            is Hydration -> HYDRATION_TASK_TYPE
            is Medication -> MEDICATION_TASK_TYPE
        }
    }

    companion object {
        const val GENERAL_TASK_TYPE = "General"
        const val NUTRITION_TASK_TYPE = "Nutrition"
        const val HYDRATION_TASK_TYPE = "Hydration"
        const val MEDICATION_TASK_TYPE = "Medication"
    }
}