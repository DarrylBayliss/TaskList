package com.darrylbayliss.carelist.data.repo

import com.darrylbayliss.carelist.data.api.TasksApi
import com.darrylbayliss.carelist.data.db.AppDatabase
import com.darrylbayliss.carelist.data.db.entities.TaskEntity
import com.darrylbayliss.carelist.data.mappers.TaskMapper
import com.darrylbayliss.carelist.data.models.Task
import com.darrylbayliss.carelist.helpers.ConnectivityChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class TasksRepoImpl(
    private val tasksApi: TasksApi,
    private val taskMapper: TaskMapper,
    private val connectivityChecker: ConnectivityChecker,
    private val database: AppDatabase
) : TasksRepo {

    override suspend fun getTasks(): List<Task> {

        return withContext(Dispatchers.IO) {

            if (connectivityChecker.isConnectedToNetwork) {
                val tasks = tasksApi.downloadTasks().map { taskDTO ->
                    taskMapper.toTask(taskDTO)
                }

                cacheTasks(tasks)

                tasks
            } else {
                database.taskDao().getAll().map { taskEntity ->
                    taskMapper.toTask(taskEntity)
                }
            }
        }
    }

    private suspend fun cacheTasks(tasks: List<Task>) {

        val taskEntities = tasks.map { task ->
            TaskEntity(
                task.id,
                task.name,
                task.description,
                task.type
            )
        }

        database.taskDao().insertAll(taskEntities)
    }
}
