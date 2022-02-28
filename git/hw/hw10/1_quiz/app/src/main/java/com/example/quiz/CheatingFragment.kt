package com.example.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.quiz.databinding.CheatingFragmentBinding

class CheatingFragment:Fragment(R.layout.cheating_fragment) {

    private lateinit var binding:CheatingFragmentBinding

    private val viewModel:MainViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CheatingFragmentBinding.bind(view)

        cheating()
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