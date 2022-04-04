package com.example.taskmanager.ui.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentTimePickerBinding

class TimePickerDialog:DialogFragment(R.layout.fragment_time_picker) {

    private lateinit var getTime: GetTime

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentTimePickerBinding.bind(view)

        binding.selectTime.setOnClickListener {
            val time = binding.timePicker.let {
                it.hour.toString() + ":" + it.minute.toString()
            }

            getTime.getTimeFromDialog(time)

            dismiss()
        }
    }

    interface GetTime{
        fun getTimeFromDialog(time:String)
    }

    fun setGetTime(getTime: GetTime){
        this.getTime = getTime
    }
}