package com.example.netflix.ui.profile

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.netflix.R
import com.example.netflix.databinding.ProfileFragmentBinding
import com.example.netflix.ui.HomeViewModel

class FragmentProfile : Fragment(R.layout.profile_fragment) {


    lateinit var activityResultLauncher: ActivityResultLauncher<Void>
    private val viewModel: HomeViewModel by activityViewModels()
    lateinit var binding: ProfileFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ProfileFragmentBinding.bind(view)

        viewModel.getIsRegistered().observe(viewLifecycleOwner) {
            if (!it) getInformation()
            else showProfile()

            binding.isRegistered = it
        }
    }

    private fun showProfile() {
        viewModel.getProfile().observe(viewLifecycleOwner) {
            binding.name = it.name
            binding.email = it.email
            binding.userName = it.userName
            binding.phoneNumber = it.phoneNumber
        }
    }

    private fun getInformation() {
        getImageFromCamera()
        getInfoFromText()
    }

    private fun getInfoFromText() {
        binding.profileRegisterButton.setOnClickListener {
            if (binding.profileUserNameEd.text.toString() == "") Toast.makeText(
                requireContext(),
                "enter your name",
                Toast.LENGTH_SHORT
            ).show()
            else if (binding.profileEmailEd.text.toString() == "") Toast.makeText(
                requireContext(),
                "enter your email",
                Toast.LENGTH_SHORT
            ).show()
            else {
                viewModel.createProfile(
                    binding.profileNameEd.text.toString(),
                    binding.profileEmailEd.text.toString(),
                    binding.profilePhoneNumberEd.text.toString(),
                    binding.profileUserNameEd.text.toString()
                )
            }
        }
    }

    fun getImageFromCamera() {
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) {
            binding.profileImage.setImageBitmap(it)

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

            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                activityResultLauncher.launch(null)
            } else Toast.makeText(requireContext(), "there is no camera", Toast.LENGTH_SHORT).show()
        }
    }

}