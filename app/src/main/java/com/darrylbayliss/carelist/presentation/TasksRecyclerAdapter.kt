package com.darrylbayliss.carelist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.darrylbayliss.carelist.R
import com.darrylbayliss.carelist.data.models.*
import com.darrylbayliss.carelist.databinding.TaskItemViewHolderBinding

class TasksRecyclerAdapter(private val tasks: MutableList<Task>) :
    RecyclerView.Adapter<TaskItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val binding = TaskItemViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TaskItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {

        val task = tasks[position]

        holder.binding.taskNameTextView.text = task.name
        holder.binding.taskDescriptionTextView.text = task.description

        val drawableId = when (task.type) {
            is General -> R.drawable.general
            is Hydration -> R.drawable.hydration
            is Medication -> R.drawable.medication
            is Nutrition -> R.drawable.nutrition
        }

        holder.binding.taskTypeImageView.setImageDrawable(
            ContextCompat.getDrawable(
                holder.itemView.context,
                drawableId
            )
        )
    }

    override fun getItemCount(): Int {
        return tasks.count()
    }

    fun updateTasks(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        this.notifyDataSetChanged()
    }
}