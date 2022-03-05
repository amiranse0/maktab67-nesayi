package com.example.fliker

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizretrofit.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    var listImages = MutableLiveData<List<String>>()

    fun getImageFromServer() {
        val hashMapQuery = hashMapOf(
            "api_key" to "1c04e05bce6e626247758d120b372a73",
            "method" to "flickr.photos.getPopular",
            "user_id" to "34427466731@N01",
            "extras" to "url_s",
            "format" to "json",
            "nojsoncallback" to "1",
            "per_page" to "100",
            "page" to "1"
        )

        val call = NetworkManager.service.getImage(hashMapQuery)
        call.enqueue(object : Callback<ImageProperties>{
            override fun onResponse(
                call: Call<ImageProperties>,
                response: Response<ImageProperties>
            ) {
                listImages.postValue(response.body()?.photos?.photo?.map {
                    it.url_s
                })
                Log.d("TAG", listImages.value?.size.toString())
            }

            override fun onFailure(call: Call<ImageProperties>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }
}