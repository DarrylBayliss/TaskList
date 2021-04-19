package com.darrylbayliss.carelist.presentation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.darrylbayliss.carelist.R
import com.darrylbayliss.carelist.data.models.General
import com.darrylbayliss.carelist.data.models.Hydration
import com.darrylbayliss.carelist.data.models.Medication
import com.darrylbayliss.carelist.data.models.Nutrition
import com.darrylbayliss.carelist.databinding.TaskFragmentBinding

class TasksFragment : Fragment() {

    companion object {
        fun newInstance() = TasksFragment()
    }

    private lateinit var viewModel: TasksViewModel

    private lateinit var binding: TaskFragmentBinding

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val filteredTask = when (item.itemId) {
            R.id.general_task_type_filter_menu_item -> General
            R.id.medication_task_type_filter_menu_item -> Medication
            R.id.hydration_task_type_filter_menu_item -> Hydration
            R.id.nutrition_task_type_filter_menu_item -> Nutrition
            else -> General
        }

        val isChecked = !item.isChecked
        item.isChecked = isChecked
        viewModel.filterTasks(filteredTask, isChecked)

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        val adapter = TasksRecyclerAdapter(mutableListOf())

        binding.tasksRecyclerView.adapter = adapter
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.banner.setRightButtonListener {
            binding.banner.dismiss()
        }

        val viewModelFactory = TasksViewModelFactory(requireContext())
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TasksViewModel::class.java)

        viewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        viewModel.filteredTasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        viewModel.connectedToInternet.observe(this) { connectedToInternet ->
            if (connectedToInternet) {
                binding.banner.dismiss()
            } else {
                binding.banner.show()
            }
        }

        viewModel.setup()
    }
}