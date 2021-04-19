package com.darrylbayliss.carelist.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.darrylbayliss.carelist.data.api.TasksApi
import com.darrylbayliss.carelist.data.db.AppDatabase
import com.darrylbayliss.carelist.data.mappers.TaskMapper
import com.darrylbayliss.carelist.data.repo.TasksRepo
import com.darrylbayliss.carelist.data.repo.TasksRepoImpl
import com.darrylbayliss.carelist.domain.usecase.GetTasksUseCase
import com.darrylbayliss.carelist.domain.usecase.GetTasksUseCaseImpl
import com.darrylbayliss.carelist.helpers.ConnectivityChecker
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DependencyInjector {

    companion object {

        private lateinit var tasksApi: TasksApi

        private var tasksRepo: TasksRepo? = null

        private lateinit var taskMapper: TaskMapper

        lateinit var connectivityChecker: ConnectivityChecker

        private lateinit var database: AppDatabase

        fun setup(context: Context) {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://adam-deleteme.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            tasksApi = retrofit.create(TasksApi::class.java)

            taskMapper = TaskMapper()

            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityChecker = ConnectivityChecker(connectivityManager)

            database = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "carelist"
            ).build()
        }

        fun provideGetTasksUseCase(): GetTasksUseCase {
            return GetTasksUseCaseImpl(provideTasksRepo())
        }

        private fun provideTasksRepo(): TasksRepo {

            if (tasksRepo == null) {
                tasksRepo = TasksRepoImpl(
                    tasksApi,
                    taskMapper,
                    connectivityChecker,
                    database
                )
            }

            return tasksRepo as TasksRepo
        }
    }
}