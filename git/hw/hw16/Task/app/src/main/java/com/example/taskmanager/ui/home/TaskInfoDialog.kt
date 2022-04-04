package com.example.taskmanager.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.FragmentTaskInfoBinding
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.home.viewmodel.SharedViewModel
import java.io.ByteArrayOutputStream

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

        getImageFromGallery()

        editTask()

        deleteTask()

    }

    private fun deleteTask() {
        binding.deleteTaskInfo.setOnClickListener{
            viewModel.deleteTask(task)
            dismiss()
        }
    }

    private fun editTask() {
        binding.titleTaskInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                task.title = binding.titleTaskInfo.text.toString()
                viewModel.updateTask(task)
            }
        })

        binding.descriptionTaskInfo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                task.description = binding.descriptionTaskInfo.text.toString()
                viewModel.updateTask(task)
            }
        })

        editTime()

        editDate()

        changeSituation()
    }

    private fun editDate() {
        binding.textInputLayout4.setEndIconOnClickListener {
            val dateDialog = DatePickerDialog()
            dateDialog.setGetDate(object : DatePickerDialog.GetDate{
                override fun getDateFromDialog(date: String) {
                    task.date = date
                    binding.dateTaskInfo.setText(date)
                    viewModel.updateTask(task)
                }
            })
            dateDialog.show(parentFragmentManager, "Date")
        }
    }

    private fun editTime() {
        binding.textInputLayout3.setEndIconOnClickListener {
            val timeDialog = TimePickerDialog()
            timeDialog.setGetTime(object : TimePickerDialog.GetTime {
                override fun getTimeFromDialog(time: String) {
                    task.time = time
                    binding.timeTaskInfo.setText(time)
                    viewModel.updateTask(task)
                }
            })
            timeDialog.show(parentFragmentManager, "Time")
        }
    }

    private fun changeSituation() {
        binding.radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                if (binding.toDoRadioButton.isChecked) task.situationOfTask = SituationOfTask.TODO
                else if (binding.doingRadioButton.isChecked) task.situationOfTask = SituationOfTask.DOING
                else if (binding.doneRadioButton.isChecked) task.situationOfTask = SituationOfTask.DONE

                viewModel.updateTask(task)
            }
        })
    }

    private fun share() {
        binding.shareTaskInfo.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, task.toString())
            val chooser = Intent.createChooser(intent, "Share Task")
            requireContext().startActivity(chooser)
        }
    }

    private fun showInfo() {

        binding.dateTaskInfo.setText(task.date)
        binding.timeTaskInfo.setText(task.time)
        binding.titleTaskInfo.setText(task.title)
        binding.descriptionTaskInfo.setText(task.description)
        val picture = task.picture
        if (picture != null) {
            binding.imageTaskInfo.load(BitmapFactory.decodeByteArray(picture, 0, picture.size))
        }

        when (task.situationOfTask) {
            SituationOfTask.TODO -> binding.toDoRadioButton.isChecked = true
            SituationOfTask.DONE -> binding.doneRadioButton.isChecked = true
            SituationOfTask.DOING -> binding.doingRadioButton.isChecked = true
        }
    }

    private fun getImageFromGallery() {
        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val stream = requireContext().getContentResolver().openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(stream)

            binding.imageTaskInfo.load(bitmap)

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val byteArray: ByteArray = bos.toByteArray()

            task.picture = byteArray
            viewModel.setImage(task)
        }

        binding.imageTaskInfo.setOnClickListener {
            loadImage.launch("image/*")
        }
    }

}