package com.example.netflix

import android.util.Log
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    var images = mutableListOf<MyImage>()
    fun addNewImage(image: MyImage){
        images.add(image)
    }
    fun addToFavorite(pos:Int){
        images[pos].isFavorite = true
    }

    fun removeFromFavorite(pos: Int){
        images[pos].isFavorite = false
    }

    fun getSize() = images.size

    fun getFavorites():MutableList<MyImage>{
        var favoriteImages = mutableListOf<MyImage>().let {
            for (i in images){
                if (i.isFavorite) it.add(i)
            }
            it
        }

        Log.d("TAG", favoriteImages.size.toString())

        return favoriteImages
    }
}