package com.example.fliker.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fliker.data.model.ImageProperties
import com.example.fliker.data.remote.ApiResponse
import com.example.fliker.data.remote.NetworkManager

class MyViewModel : ViewModel() {
    var listImages = MutableLiveData<List<String>>()

    fun getImageFromServer():LiveData<ApiResponse<ImageProperties>> {
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

        return NetworkManager.service.getImage(hashMapQuery)

    }
}