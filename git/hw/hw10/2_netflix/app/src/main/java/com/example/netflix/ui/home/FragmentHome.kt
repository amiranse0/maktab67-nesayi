package com.example.netflix.ui.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflix.MyMovie
import com.example.netflix.R
import com.example.netflix.databinding.HomeFragmentBinding
import com.example.netflix.ui.HomeViewModel

class FragmentHome : Fragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: HomeFragmentBinding
    private lateinit var recyclerAdaptor: HomeRecyclerAdaptor
    private val listMovies = mutableListOf<MyMovie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)

        viewModel.getMovieFromServer()

        draw()

        clickFavourite()
    }

    private fun clickFavourite() {
        recyclerAdaptor.setItemClickFavourite(object: HomeRecyclerAdaptor.ClickFavourite {
            override fun iconClicked(pos: Int, icon: ImageView) {
                val boolean = viewModel.clickFavourite(pos)
                if (boolean) icon.setImageResource(R.drawable.favorite_icon_choosed)
                else icon.setImageResource(R.drawable.favorite_icon)
            }
        })
    }

    private fun draw() {
        val recyclerView = binding.recyclerView
        viewModel.getMovies().observe(viewLifecycleOwner){
            listMovies.clear()
            listMovies.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()

        }

        recyclerAdaptor = HomeRecyclerAdaptor(listMovies, viewLifecycleOwner)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        recyclerView.adapter = recyclerAdaptor
    }
}