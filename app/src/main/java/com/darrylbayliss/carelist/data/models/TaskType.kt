package com.darrylbayliss.carelist.data.models

sealed class TaskType

object General : TaskType()
object Hydration : TaskType()
object Medication : TaskType()
object Nutrition : TaskType()

