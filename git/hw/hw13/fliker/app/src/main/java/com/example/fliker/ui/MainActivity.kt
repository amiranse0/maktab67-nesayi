package com.example.fliker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliker.data.remote.ApiErrorResponse
import com.example.fliker.data.remote.ApiSuccessResponse
import com.example.fliker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdaptor: RecyclerAdaptor
    private var listImages =  mutableListOf<String>()
    private val viewModel: MyViewModel by lazy { MyViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getImageFromServer()

        putImageIntoView()
    }

    private fun putImageIntoView() {

        recyclerView = binding.rec

        viewModel.getImageFromServer().observe(this) { res ->
            if (res is ApiSuccessResponse) {
                listImages.clear()
                val newList: List<String>? = res.body.photos.photo.map {
                    it.url_s
                }
                if (newList != null) {
                    listImages.addAll(newList)
                }
                recyclerAdaptor.notifyDataSetChanged()

            }

            else if (res is ApiErrorResponse) {
                Log.d("TAG", "onCreate: ${res.errorMessage}")
            }
        }

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerAdaptor = RecyclerAdaptor(listImages, this)
        recyclerView.adapter = recyclerAdaptor
    }
}