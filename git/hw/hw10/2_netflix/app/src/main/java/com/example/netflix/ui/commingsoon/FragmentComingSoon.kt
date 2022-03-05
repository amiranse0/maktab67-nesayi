package com.example.netflix.ui.commingsoon

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.netflix.MyMovie
import com.example.netflix.R
import com.example.netflix.databinding.ComingSoonFragmentBinding
import com.example.netflix.getmoviewithretrofit.NetworkManager
import com.example.netflix.ui.home.HomeRecyclerAdaptor
import com.example.netflix.ui.home.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentComingSoon : Fragment(R.layout.coming_soon_fragment) {

    private lateinit var binding: ComingSoonFragmentBinding

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var recyclerAdaptor:ComingSoonRecyclerAdaptor
    private val listMovies = mutableListOf<MyMovie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ComingSoonFragmentBinding.bind(view)

        viewModel.getNewMoviesFromServer()

        draw()
    }

    private fun draw() {
        val recyclerView = binding.comingSoonRc
        viewModel.getNewMovies().observe(viewLifecycleOwner){
            listMovies.clear()
            listMovies.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
        }
        val recyclerAdaptor = HomeRecyclerAdaptor(listMovies, viewLifecycleOwner)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        recyclerView.adapter = recyclerAdaptor
    }
}