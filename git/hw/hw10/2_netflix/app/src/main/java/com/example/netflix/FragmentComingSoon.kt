package com.example.netflix

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.netflix.databinding.ComingSoonFragmentBinding
import com.example.netflix.getmoviewithretrofit.Movie
import com.example.netflix.getmoviewithretrofit.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentComingSoon : Fragment(R.layout.coming_soon_fragment) {

    private lateinit var binding: ComingSoonFragmentBinding

    val viewModel: ComingSoonViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = ComingSoonFragmentBinding.bind(view)

        if (viewModel.listNewMovies.size == 0) getImages()

        draw()
    }


    private fun getImages() {

        Log.d("TAG", "this is get images")

        val movie1 = NetworkManager.service.getMovie(
            viewModel.comingSoonMovies[0].first,
            viewModel.comingSoonMovies[0].second,
            getString(R.string.apikey)
        )

        movie1.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {

                val image = ResultNewMovies(
                    response.body()?.Poster,
                    response.body()?.Title,
                    response.body()?.Plot
                )
                viewModel.addNewPoster(image)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        })

        val movie2 = NetworkManager.service.getMovie(
            viewModel.comingSoonMovies[1].first,
            viewModel.comingSoonMovies[1].second,
            getString(R.string.apikey)
        )

        movie2.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val image = ResultNewMovies(
                    response.body()?.Poster,
                    response.body()?.Title,
                    response.body()?.Plot
                )
                viewModel.addNewPoster(image)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        })

        val movie3 = NetworkManager.service.getMovie(
            viewModel.comingSoonMovies[2].first,
            viewModel.comingSoonMovies[2].second,
            getString(R.string.apikey)
        )

        movie3.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val image = ResultNewMovies(
                    response.body()?.Poster,
                    response.body()?.Title,
                    response.body()?.Plot
                )
                viewModel.addNewPoster(image)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }

    private fun draw() {

        val recyclerView = binding.comingSoonRc
        val recyclerAdaptor = ComingSoonRecyclerAdaptor(viewModel, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor
    }
}