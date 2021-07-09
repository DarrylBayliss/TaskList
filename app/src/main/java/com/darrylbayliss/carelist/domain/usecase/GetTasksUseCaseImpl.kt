package com.darrylbayliss.carelist.domain.usecase

import com.darrylbayliss.carelist.data.models.Task
import com.darrylbayliss.carelist.data.repo.TasksRepo

class GetTasksUseCaseImpl(private val tasksRepo: TasksRepo) : GetTasksUseCase {

    override suspend fun getTasks(): List<Task> {
        return tasksRepo.getTasks()

        
    }
}