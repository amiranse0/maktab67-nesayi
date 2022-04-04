package com.example.taskmanager.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.UserNameClass
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.FragmentDoneBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.home.viewmodel.SharedViewModel

class DoneFragment:Fragment(R.layout.fragment_done) {

    private val viewModel: SharedViewModel by activityViewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    private lateinit var recyclerAdaptor: MyRecyclerAdaptor

    private lateinit var binding: FragmentDoneBinding

    private var doneList = mutableListOf<Task>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDoneBinding.bind(view)

        binding.isEmpty = false

        draw()

        showInfoDialog()
    }

    private fun showInfoDialog() {
        recyclerAdaptor.setToClickOnTask(object : MyRecyclerAdaptor.ClickOnTask {
            override fun clickOnTask(position: Int, view: View?) {
                val taskDialogFragment = TaskInfoDialog(doneList[position])
                taskDialogFragment.show(parentFragmentManager, "Info")
            }
        })
    }

    private fun draw() {

        val username = UserNameClass.username

        viewModel.getTasks(username, SituationOfTask.DONE).observe(viewLifecycleOwner){
            doneList.clear()
            doneList.addAll(it)
            binding.isEmpty = it.isEmpty()
            recyclerAdaptor.notifyDataSetChanged()
        }

        recyclerAdaptor = MyRecyclerAdaptor(doneList)

        binding.recyclerViewDone.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDone.adapter = recyclerAdaptor
    }

    override fun onResume() {
        viewModel.setFragmentName("DONE")
        super.onResume()
    }
}