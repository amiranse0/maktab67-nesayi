package com.example.netflix

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.netflix.databinding.ProfileFragmentBinding
import org.json.JSONStringer
import java.util.jar.Manifest

class FragmentProfile : Fragment(R.layout.profile_fragment) {


    lateinit var activityResultLauncher: ActivityResultLauncher<Void>

    lateinit var binding: ProfileFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ProfileFragmentBinding.bind(view)

        getInformation()
    }

    private fun getInformation() {

        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) {
            binding.profileImage.setImageBitmap(it)

        }
        binding.profilePictureAdd.setOnClickListener {

            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(requireActivity(),
                    arrayOf(android.Manifest.permission.CAMERA), 100)
            }

            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                activityResultLauncher.launch(null)
            } else Toast.makeText(requireContext(), "there is no camera", Toast.LENGTH_SHORT).show()
        }

        binding.profileRegisterButton.setOnClickListener {

        }
    }

}