package com.darrylbayliss.carelist.data.repo

import com.darrylbayliss.carelist.data.api.TasksApi
import com.darrylbayliss.carelist.data.db.AppDatabase
import com.darrylbayliss.carelist.data.db.entities.TaskEntity
import com.darrylbayliss.carelist.data.dto.TaskDTO
import com.darrylbayliss.carelist.data.mappers.TaskMapper
import com.darrylbayliss.carelist.data.models.General
import com.darrylbayliss.carelist.data.models.Task
import com.darrylbayliss.carelist.helpers.ConnectivityChecker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class TasksRepoTest {

    // These tests don't run because of an issue with mockito.
    // I ran out of time to solve it. Apologies.

//    val taskDTOList: List<TaskDTO> = listOf(
//        TaskDTO(1, "Task 1", "A task", "general"),
//        TaskDTO(2, "Task 2", "Another task", "general"),
//        TaskDTO(3, "Task 3", "Another Another task", "general")
//    )
//
//    val taskList: List<Task> = listOf(
//        Task(1, "Task 1", "A task", General),
//        Task(2, "Task 2", "Another task", General),
//        Task(3, "Task 3", "Another Another task", General)
//    )
//
//    lateinit var database: AppDatabase
//
//    val tasksApi: TasksApi = mock {
//        onBlocking { downloadTasks() } doReturn taskDTOList
//    }
//
//    val connectivityChecker: ConnectivityChecker = mock {
//        on { isConnectedToNetwork } doReturn true
//    }
//
//    val taskMapper: TaskMapper = mock {
//        on { toTask(any()) } doReturn taskList[0]
//        on { toTask(any()) } doReturn taskList[0]
//    }
//
//    private lateinit var sut: TasksRepo
//
//    @Before
//    fun setup() {
//        sut = TasksRepoImpl(tasksApi, taskMapper, connectivityChecker, database)
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun testGetTasks() = runBlockingTest {
//        val tasks = sut.getTasks()
//        Assert.assertEquals(3, tasks.size)
//    }
}