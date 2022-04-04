package com.example.taskmanager.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.UserNameClass
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.FragmentDoingBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.home.viewmodel.SharedViewModel

class DoingFragment:Fragment(R.layout.fragment_doing) {

    private val viewModel: SharedViewModel by activityViewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private lateinit var binding: FragmentDoingBinding

    private var doingList = mutableListOf<Task>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDoingBinding.bind(view)

        binding.isEmpty = false

        draw()

        showInfoDialog()
    }

    private fun showInfoDialog() {
        recyclerAdaptor.setToClickOnTask(object : MyRecyclerAdaptor.ClickOnTask {
            override fun clickOnTask(position: Int, view: View?) {
                val taskDialogFragment = TaskInfoDialog(doingList[position])
                taskDialogFragment.show(parentFragmentManager, "Info")
            }
        })
    }

    private fun draw() {

        val username = UserNameClass.username

        viewModel.getTasks(username, SituationOfTask.DOING).observe(viewLifecycleOwner){
            doingList.clear()
            doingList.addAll(it)
            binding.isEmpty = it.isEmpty()
            recyclerAdaptor.notifyDataSetChanged()
        }

        recyclerAdaptor = MyRecyclerAdaptor(doingList)

        binding.recyclerViewDoing.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDoing.adapter = recyclerAdaptor
    }


    override fun onResume() {
        viewModel.setFragmentName("DOING")
        super.onResume()
    }


}