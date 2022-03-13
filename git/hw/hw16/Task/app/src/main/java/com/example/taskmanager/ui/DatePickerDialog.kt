package com.example.taskmanager.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentDatePickerBinding

class DatePickerDialog:DialogFragment(R.layout.fragment_date_picker) {
    private lateinit var binding:FragmentDatePickerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDatePickerBinding.bind(view)


    }
}