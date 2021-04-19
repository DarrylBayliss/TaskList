package com.darrylbayliss.carelist.data.api

import com.darrylbayliss.carelist.data.dto.TaskDTO
import retrofit2.http.GET

interface TasksApi {

    @GET("/tasks.json")
    suspend fun downloadTasks() : List<TaskDTO>
}