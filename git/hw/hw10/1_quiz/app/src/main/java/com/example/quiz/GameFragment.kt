package com.example.quiz

import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quiz.databinding.FragmentGameBinding


class GameFragment:Fragment(R.layout.fragment_game) {

    private lateinit var binding:FragmentGameBinding
    private val viewModel:MainViewModel by  activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)
        game()
    }

    private fun game(){
        getStart()
        clickNext()
        clickPrevious()
        clickTrue()
        clickFalse()
        cheatFragment()
    }

    private fun cheatFragment() {
        binding.cheatButton.setOnClickListener{
            findNavController().navigate(R.id.action_gameFragment_to_cheatingFragment)
        }
    }

    private fun getStart(){
        if (viewModel.getCounter() == 0) binding.prevButton.isEnabled = false
        if (viewModel.getCounter() == viewModel.getQuestionSize() - 1) binding.nexButton.isEnabled = false
        if (viewModel.isAnswered()){
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }
        binding.question.text = viewModel.getQuestion()
    }

    private fun clickNext(){
        binding.nexButton.setOnClickListener {
            binding.prevButton.isEnabled = true
            viewModel.nextQuestion()
            if (viewModel.isAnswered()){
                binding.falseButton.isEnabled = false
                binding.trueButton.isEnabled = false
            }
            else {
                binding.trueButton.isEnabled = true
                binding.falseButton.isEnabled = true
            }

            if (viewModel.getCounter() == viewModel.getQuestionSize() - 1){
                binding.nexButton.isEnabled = false
            }

            binding.question.text = viewModel.getQuestion()
        }
    }

    private fun clickPrevious(){
        binding.prevButton.setOnClickListener {
            binding.nexButton.isEnabled = true
            viewModel.previousQuestion()
            if (viewModel.isAnswered()){
                binding.falseButton.isEnabled = false
                binding.trueButton.isEnabled = false
            }
            else {
                binding.trueButton.isEnabled = true
                binding.falseButton.isEnabled = true
            }

            if (viewModel.getCounter() == 0){
                binding.prevButton.isEnabled = false
            }

            binding.question.text = viewModel.getQuestion()
        }
    }

    private fun clickTrue(){
        binding.trueButton.setOnClickListener{
            if (viewModel.isCheating()) {
                Toast.makeText(requireContext(), "cheating is wrong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.answered()
                if (viewModel.getAnswer()) {
                    Toast.makeText(requireContext(), "right answer", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "wrong answer", Toast.LENGTH_SHORT).show()
                }

                binding.trueButton.isEnabled = false
                binding.falseButton.isEnabled = false
            }
        }
    }

    private fun clickFalse(){
        binding.falseButton.setOnClickListener{
            if (viewModel.isCheating()) {
                Toast.makeText(requireContext(), "cheating is wrong", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.answered()
                if (viewModel.getAnswer()) {
                    Toast.makeText(requireContext(), "wrong answer", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "right answer", Toast.LENGTH_SHORT).show()
                }

                binding.trueButton.isEnabled = false
                binding.falseButton.isEnabled = false
            }
        }
    }
}