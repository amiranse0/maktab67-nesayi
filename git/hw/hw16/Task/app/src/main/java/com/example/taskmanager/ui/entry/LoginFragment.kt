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
import com.example.taskmanager.databinding.FragmentLoginBinding
import com.example.taskmanager.ui.HomeActivity
import com.example.taskmanager.ui.viewmodel.CustomViewModelFactory
import com.example.taskmanager.ui.viewmodel.SharedViewModel

class LoginFragment:Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels(factoryProducer = {
        CustomViewModelFactory((requireActivity().application as App).serviceLocator.repository)
    })

    private lateinit var binding:FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        goToHomeActivity()
        goToSignUpFragment()
    }

    private fun goToSignUpFragment() {
        binding.goToSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun goToHomeActivity() {
        binding.loginButton.setOnClickListener{
            val username = binding.usernameLoginEd.text.toString()
            viewModel.usernameLiveData.postValue(username)
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
        }
    }
}