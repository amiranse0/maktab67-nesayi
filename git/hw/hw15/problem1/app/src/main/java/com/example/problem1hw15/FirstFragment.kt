package com.example.problem1hw15

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.problem1hw15.databinding.FirstFragmentBinding

class FirstFragment:Fragment(R.layout.first_fragment) {

    private lateinit var binding: FirstFragmentBinding
    private lateinit var recyclerAdaptor: MyRecyclerAdaptor
    lateinit var recyclerView: RecyclerView
    private val viewModel:MyViewModel by activityViewModels()
    private val listItems = mutableListOf<Item>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FirstFragmentBinding.bind(view)

        showItems()

        clickItems()
    }

    private fun clickItems() {
        recyclerAdaptor.setClickItem(object : MyRecyclerAdaptor.ClickItem{
            @SuppressLint("ResourceAsColor")
            override fun click(position: Int, view: View?) {
                val isFav = viewModel.clickFav(position)

            }
        })
    }

    private fun showItems() {
        recyclerView = binding.recyclerView

        viewModel.getCities().observe(viewLifecycleOwner){
            listItems.clear()
            listItems.addAll(it)
        }

        recyclerAdaptor = MyRecyclerAdaptor(listItems)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor

    }
}