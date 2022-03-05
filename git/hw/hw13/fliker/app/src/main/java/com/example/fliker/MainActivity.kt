package com.example.fliker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliker.databinding.ActivityMainBinding
import com.example.quizretrofit.RecyclerAdaptor

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

        viewModel.listImages.observe(this) {
            listImages.clear()
            listImages.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
        }

        recyclerView.layoutManager = GridLayoutManager(this, 4)
        recyclerAdaptor = RecyclerAdaptor(listImages, this)
        recyclerView.adapter = recyclerAdaptor
    }
}