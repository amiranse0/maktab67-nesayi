package com.example.taskmanager.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.FragmentToDoBinding
import com.example.taskmanager.di.ServiceLocator
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel
import java.sql.Date
import java.sql.Time

class ToDoFragment : Fragment(R.layout.fragment_to_do) {

    private val viewModel: SharedViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private lateinit var binding: FragmentToDoBinding

    private var toDoList = mutableListOf<Task>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentToDoBinding.bind(view)

        binding.isEmpty = false

        draw()

        showInfoDialog()
    }

    private fun showInfoDialog() {
        recyclerAdaptor.setToClickOnTask(object : MyRecyclerAdaptor.ClickOnTask {
            override fun clickOnTask(position: Int, view: View?) {
                val addDialogFragment = TaskInfoDialog()
                addDialogFragment.show(parentFragmentManager, "Info")
            }
        })
    }

    private fun draw() {

        viewModel.getTasks("Amirabbas", SituationOfTask.TODO).observe(viewLifecycleOwner) {
            toDoList.clear()
            toDoList.addAll(it)
            binding.isEmpty = it.isEmpty()
            recyclerAdaptor.notifyDataSetChanged()
        }

        recyclerAdaptor = MyRecyclerAdaptor(toDoList)

        binding.recyclerViewToDo.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewToDo.adapter = recyclerAdaptor
    }
}