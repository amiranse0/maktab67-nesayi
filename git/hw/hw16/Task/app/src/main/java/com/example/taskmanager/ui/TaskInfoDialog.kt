package com.example.taskmanager.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentDoingBinding
import com.example.taskmanager.databinding.FragmentTaskInfoBinding

class TaskInfoDialog:DialogFragment(R.layout.fragment_task_info) {

    lateinit var binding: FragmentTaskInfoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTaskInfoBinding.bind(view)
    }
}