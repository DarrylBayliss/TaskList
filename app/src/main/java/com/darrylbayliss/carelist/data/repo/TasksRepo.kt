package com.darrylbayliss.carelist.data.repo

import com.darrylbayliss.carelist.data.models.Task

interface TasksRepo {

    suspend fun getTasks() : List<Task>
}