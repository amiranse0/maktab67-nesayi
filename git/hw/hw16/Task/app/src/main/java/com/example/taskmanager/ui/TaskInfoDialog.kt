package com.example.taskmanager.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import coil.load
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.SituationOfTask
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.FragmentDoingBinding
import com.example.taskmanager.databinding.FragmentTaskInfoBinding
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel
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
        val picture = task.picture
        if (picture != null){
            binding.imageTaskInfo.load(BitmapFactory.decodeByteArray(picture, 0 , picture.size))
        }

        when(task.situationOfTask){
            SituationOfTask.TODO -> binding.toDoRadioButton.isChecked = true
            SituationOfTask.DONE -> binding.doneRadioButton.isChecked = true
            SituationOfTask.DOING -> binding.doingRadioButton.isChecked = true
        }
    }

    private fun getImageFromGallery(){
        val loadImage = registerForActivityResult(ActivityResultContracts.GetContent()){
            val stream = requireContext().getContentResolver().openInputStream(it)
            val bitmap = BitmapFactory.decodeStream(stream)

            binding.imageTaskInfo.load(bitmap)

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 , bos)
            val byteArray: ByteArray = bos.toByteArray()

            task.picture = byteArray
            viewModel.setImage(task)
        }

        binding.imageTaskInfo.setOnClickListener {
            loadImage.launch("image/*")
        }
    }

}