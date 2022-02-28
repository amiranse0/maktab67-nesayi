package com.example.netflix

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflix.databinding.HomeFragmentBinding
import com.example.netflix.getmoviewithretrofit.Movie
import com.example.netflix.getmoviewithretrofit.MovieNames
import com.example.netflix.getmoviewithretrofit.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentHome : Fragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var binding: HomeFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        if (viewModel.images.size == 0) getPosters()

        recyclerHandler()
    }

    private fun getPosters() {
        for (item in MovieNames().movies) {
            val movie = NetworkManager.service.getMovie(item.key, item.value, getString(R.string.apikey))
            movie.enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    var image = MyImage(response.body()!!.Title, response.body()!!.Poster, false)
                    viewModel.addNewImage(image)
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

    private fun recyclerHandler() {
        val recyclerView = binding.recyclerView
        val recyclerAdaptor = HomeRecyclerAdaptor(viewModel, requireContext())
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = recyclerAdaptor
    }

}