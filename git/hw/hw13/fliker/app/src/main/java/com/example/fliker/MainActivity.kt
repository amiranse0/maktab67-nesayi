package com.example.fliker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fliker.databinding.ActivityMainBinding
import com.example.quizretrofit.ImageProperties
import com.example.quizretrofit.NetworkManager
import com.example.quizretrofit.RecyclerAdaptor
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdaptor: RecyclerAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImages()
    }

    fun getImages(){
        val hashMapQuery = hashMapOf("api_key" to "1c04e05bce6e626247758d120b372a73",
            "method" to "flickr.photos.getPopular",
            "user_id" to "34427466731@N01",
            "extras" to "url_s",
            "format" to "json",
            "nojsoncallback" to "1",
            "per_page" to "5",
            "page" to "1")

        Log.d("TAG","S1")

        val images = NetworkManager.service.getImage(hashMapQuery)
        images.enqueue(object : retrofit2.Callback<List<ImageProperties>> {
            override fun onResponse(
                call: retrofit2.Call<List<ImageProperties>>,
                response: Response<List<ImageProperties>>
            ) {
                Log.d("TAG", response.body().toString())

                Handler(mainLooper).post { putImageIntoView(response.body() ?: emptyList()) }
            }

            override fun onFailure(call: retrofit2.Call<List<ImageProperties>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }

        })
    }

    fun putImageIntoView(imageView: List<ImageProperties>){

        Log.d("TAG","S2")

        recyclerView = binding.rec
        recyclerView.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        recyclerAdaptor = RecyclerAdaptor(imageView, this)
        recyclerView.adapter = recyclerAdaptor
    }
}