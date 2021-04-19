package com.darrylbayliss.carelist.domain.usecase

import com.darrylbayliss.carelist.data.models.Task

interface GetTasksUseCase {

    suspend fun getTasks(): List<Task>
}