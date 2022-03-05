package com.example.netflix.ui.favourite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflix.MyMovie
import com.example.netflix.R
import com.example.netflix.databinding.FavoriteFragmentBinding
import com.example.netflix.ui.favourite.FavoriteRecyclerAdaptor
import com.example.netflix.ui.home.HomeRecyclerAdaptor
import com.example.netflix.ui.home.HomeViewModel

class FragmentFavorite:Fragment(R.layout.favorite_fragment) {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var recyclerAdaptor:HomeRecyclerAdaptor
    private val listMovies = mutableListOf<MyMovie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FavoriteFragmentBinding.bind(view)

        draw()
    }

    private fun draw(){
        val recyclerView = binding.favoriteRecyclerView
        viewModel.getMovies().observe(viewLifecycleOwner){
            listMovies.clear()
            listMovies.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
        }
        val recyclerAdaptor = FavoriteRecyclerAdaptor(listMovies, viewLifecycleOwner)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        recyclerView.adapter = recyclerAdaptor
    }
}