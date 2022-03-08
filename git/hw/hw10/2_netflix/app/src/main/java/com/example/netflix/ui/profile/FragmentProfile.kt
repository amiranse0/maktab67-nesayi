package com.example.netflix.ui.profile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.netflix.R
import com.example.netflix.databinding.ProfileFragmentBinding
import com.example.netflix.loadImage
import com.example.netflix.ui.HomeViewModel
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class FragmentProfile : Fragment(R.layout.profile_fragment) {

    private lateinit var activityResultLauncher: ActivityResultLauncher<Void>
    private val viewModel: HomeViewModel by activityViewModels()
    lateinit var binding: ProfileFragmentBinding
    private val sharedPreferences: SharedPreferences by lazy {
        requireActivity().getSharedPreferences(
            "profile_netflix",
            Context.MODE_PRIVATE
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileFragmentBinding.bind(view)

        var isRegistered = sharedPreferences.getBoolean("isRegistered", false)

        if (isRegistered) {
            getDataFromServerAndSharedPref()
        } else getDataFromUserForRegister()
    }

    private fun getDataFromServerAndSharedPref() {

        binding.image = "http://51.195.19.222/uploads/profileNetflixNesaee.jpg"

        binding.name = sharedPreferences.getString("name", "")
        binding.email = sharedPreferences.getString("email", "")
        binding.phoneNumber = sharedPreferences.getString("phoneNumber", "")
        binding.userName = sharedPreferences.getString("userName", "")
        binding.isReg = true

    }

    private fun getDataFromUserForRegister() {
        getImageFromCamera()
        getInfoFromText()
    }

    private fun getInfoFromText() {
        binding.profileRegisterButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            if (binding.profileNameEd.text.toString() == "") Toast.makeText(
                requireContext(),
                "name needed",
                Toast.LENGTH_SHORT
            ).show()
            else if (binding.profileEmailEd.text.toString() == "") Toast.makeText(
                requireContext(),
                "email needed",
                Toast.LENGTH_SHORT
            ).show()
            else {
                with(editor) {
                    putBoolean("isRegistered", true)
                    putString("name", binding.profileNameEd.text.toString())
                    putString("email", binding.profileEmailEd.text.toString())
                    putString("phoneNumber", binding.profilePhoneNumberEd.text.toString())
                    putString("userName", binding.profileUserNameEd.text.toString())
                    apply()
                    getDataFromServerAndSharedPref()
                }
            }
        }
    }

    private fun getImageFromCamera() {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) {
            binding.profileImage.setImageBitmap(it)
            val stream = ByteArrayOutputStream()
            it.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            val image = stream.toByteArray()
            viewModel.postProfileImage(image)
        }
        binding.profilePictureAdd.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.CAMERA), 100
                )
            }

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                activityResultLauncher.launch(null)
            } else Toast.makeText(requireContext(), "there is no camera", Toast.LENGTH_SHORT).show()
        }
    }
}