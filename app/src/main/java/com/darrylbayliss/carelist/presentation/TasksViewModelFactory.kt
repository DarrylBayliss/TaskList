package com.darrylbayliss.carelist.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darrylbayliss.carelist.data.repo.TasksRepo
import com.darrylbayliss.carelist.di.DependencyInjector
import com.darrylbayliss.carelist.domain.usecase.GetTasksUseCase
import com.darrylbayliss.carelist.domain.usecase.GetTasksUseCaseImpl
import com.darrylbayliss.carelist.helpers.ConnectivityChecker

class TasksViewModelFactory(context: Context) : ViewModelProvider.Factory {

    init {
        DependencyInjector.setup(context)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return modelClass.getConstructor(GetTasksUseCaseImpl::class.java, ConnectivityChecker::class.java)
            .newInstance(DependencyInjector.provideGetTasksUseCase(), DependencyInjector.connectivityChecker)
    }
}