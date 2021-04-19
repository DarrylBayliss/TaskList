package com.darrylbayliss.carelist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.darrylbayliss.carelist.data.db.dao.TaskDao
import com.darrylbayliss.carelist.data.db.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(TaskTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}