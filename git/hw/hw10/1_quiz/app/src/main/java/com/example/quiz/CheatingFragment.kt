package com.example.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quiz.databinding.CheatingFragmentBinding

class CheatingFragment:Fragment(R.layout.cheating_fragment) {

    private lateinit var binding:CheatingFragmentBinding

    private val viewModel:MainViewModel by activityViewModels()

    private  val navArgs:CheatingFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CheatingFragmentBinding.bind(view)

        cheating()

        back()
    }

    private fun back() {
        binding.backButton?.setOnClickListener {
            findNavController().navigate(R.id.action_cheatingFragment_to_gameFragment)
        }
    }

    private fun cheating(){
        binding.acceptCheatingButton.setOnClickListener {
            viewModel.cheated()
            binding.cheatingText.text = viewModel.getAnswer().toString()
            binding.acceptCheatingButton.visibility = View.INVISIBLE
            binding.acceptCheatingButton.isClickable = false
        }
    }
}