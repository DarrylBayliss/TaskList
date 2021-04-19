package com.darrylbayliss.carelist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darrylbayliss.carelist.R

class TasksActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.task_activity)

    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
              .replace(R.id.container, TasksFragment.newInstance())
              .commitNow()
    }
  }
}