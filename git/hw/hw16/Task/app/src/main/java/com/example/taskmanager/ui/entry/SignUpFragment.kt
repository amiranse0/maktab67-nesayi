package com.example.taskmanager.ui.entry

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.data.model.User
import com.example.taskmanager.databinding.FragmentSignUpBinding
import com.example.taskmanager.ui.home.HomeActivity
import com.example.taskmanager.ui.home.viewmodel.CustomViewModelFactory

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel: LoginViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    lateinit var binding: FragmentSignUpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        createUser()

        backToLogin()

    }


    private fun backToLogin() {
        binding.goToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun createUser() {
        binding.signUpButton.setOnClickListener {
            val username = binding.usernameSignUpEd.text.toString()
            val password = binding.passwordSignUpEd.text.toString()
            val name = binding.nameSignUpEd.text.toString()
            val user = User(name = name, userName = username, password = password)
            viewModel.checkForConflict(username).observe(viewLifecycleOwner) {
                if (it) {
                    binding.usernameSignUpEd.error = "this username is taken"
                } else {
                    viewModel.createUser(user)

                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                }
            }

        }
    }
}