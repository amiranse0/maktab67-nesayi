package com.example.problem1hw15

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        goToFavorite()
    }

    private fun goToFavorite() {
        binding.showFavButton.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

    private fun clickItems() {
        recyclerAdaptor.setClickItem(object : MyRecyclerAdaptor.ClickItem{

            @SuppressLint("ResourceAsColor")
            override fun click(item: Item, view: View?) {
                val isFav = viewModel.clickFav(item)
                view?.isSelected = isFav == true
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