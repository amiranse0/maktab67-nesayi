package com.example.netflix

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflix.databinding.FavoriteFragmentBinding

class FragmentFavorite:Fragment(R.layout.favorite_fragment) {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: FavoriteFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FavoriteFragmentBinding.bind(view)

        Log.d("TAG", "in fragment: ${viewModel.getFavorites().size}")

        val recyclerView = binding.favoriteRecyclerView
        val recyclerAdaptor = FavoriteRecyclerAdaptor(viewModel, requireContext())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = recyclerAdaptor
    }
}