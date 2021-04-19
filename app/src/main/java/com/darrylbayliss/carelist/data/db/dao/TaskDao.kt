package com.darrylbayliss.carelist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darrylbayliss.carelist.data.db.entities.TaskEntity
import com.darrylbayliss.carelist.data.models.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskEntity")
    suspend fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskEntity>)
}