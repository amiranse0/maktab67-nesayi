package com.example.taskmanager.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.FragmentDoingBinding
import com.example.taskmanager.databinding.FragmentTaskInfoBinding
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel

class TaskInfoDialog(val task: Task) : DialogFragment(R.layout.fragment_task_info) {

    lateinit var binding: FragmentTaskInfoBinding

    private val viewModel: SharedViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTaskInfoBinding.bind(view)

        showInfo()

        share()

    }

    private fun share() {
        binding.shareTaskInfo.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, task.toString())
            val chooser = Intent.createChooser(intent, "Share Task")
            requireContext().startActivity(chooser)
        }
    }

    private fun showInfo() {
        binding.dateTaskInfo.text = task.date
        binding.timeTaskInfo.text = task.time
        binding.titleTaskInfo.text = task.title
        binding.descriptionTaskInfo.text = task.description

        when(task.situationOfTask){
            SituationOfTask.TODO -> binding.toDoRadioButton.isChecked = true
            SituationOfTask.DONE -> binding.doneRadioButton.isChecked = true
            SituationOfTask.DOING -> binding.doingRadioButton.isChecked = true
        }
    }

}