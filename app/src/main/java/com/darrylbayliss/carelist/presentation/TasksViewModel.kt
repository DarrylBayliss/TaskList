package com.darrylbayliss.carelist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darrylbayliss.carelist.data.models.Task
import com.darrylbayliss.carelist.data.models.TaskType
import com.darrylbayliss.carelist.domain.usecase.GetTasksUseCaseImpl
import com.darrylbayliss.carelist.helpers.ConnectivityChecker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(
    private val getTasksUseCase: GetTasksUseCaseImpl,
    private val connectivityChecker: ConnectivityChecker
) : ViewModel() {

    val tasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData(emptyList())
    }

    val filteredTasks: MutableLiveData<List<Task>> by lazy {
        MutableLiveData(emptyList())
    }

    val connectedToInternet: MutableLiveData<Boolean> by lazy {
        MutableLiveData(true)
    }

    private val taskFilterMap: MutableMap<TaskType, Boolean> = mutableMapOf()

    fun setup() {

        viewModelScope.launch {
            connectivityChecker.connectedToNetworkListener = { connected ->

                viewModelScope.launch(Dispatchers.Main) {
                    connectedToInternet.value = connected
                }
            }

            connectivityChecker.beginScanning()

            getTasks()
        }
    }

    private fun getTasks() {
        viewModelScope.launch {
            val tasksResult = getTasksUseCase.getTasks()
            tasks.value = tasksResult
        }
    }

    fun filterTasks(taskType: TaskType, isChecked: Boolean) {

        viewModelScope.launch {
            taskFilterMap[taskType] = isChecked
            filteredTasks.value = tasks.value?.filter { task ->
                taskFilterMap[task.type] != true
            }
        }
    }
}