package com.example.taskmanager.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentDatePickerBinding
import com.example.taskmanager.databinding.FragmentTimePickerBinding
import com.example.taskmanager.ui.viewmodel.SharedViewModel

class DatePickerDialog:DialogFragment(R.layout.fragment_date_picker) {
    private lateinit var binding:FragmentDatePickerBinding

    private lateinit var getDate: GetDate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDatePickerBinding.bind(view)

        binding.selectTimeButton.setOnClickListener {
            val date = binding.datePicker.let {
                it.year.toString() + "/" + it.month.toString() + "/" + it.dayOfMonth.toString()
            }
            getDate.getDateFromDialog(date)
            dismiss()
        }
    }

    interface GetDate{
        fun getDateFromDialog(date:String)
    }

    fun setGetDate(getDate: GetDate){
        this.getDate = getDate
    }
}