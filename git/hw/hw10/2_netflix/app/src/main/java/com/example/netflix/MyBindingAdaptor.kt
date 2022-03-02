package com.example.netflix

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage", "pos")
fun ImageView.loadImage(viewModel: HomeViewModel, pos:Int){
    Glide.with(this.context)
        .load(viewModel.getImages().value?.get(pos)?.image)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}

@BindingAdapter("onClickFavourite","pos")
fun ImageView.onClickFavourite(viewModel: HomeViewModel,pos: Int){

    if (viewModel.getImages().value?.get(pos)?.isFavorite == true) this.setImageResource(R.drawable.favorite_icon_choosed)
    if (viewModel.getImages().value?.get(pos)?.isFavorite == false) this.setImageResource(R.drawable.favorite_icon)

    this.setOnClickListener {
        val bool = viewModel.clickFavourite(pos)
        if (bool == true) this.setImageResource(R.drawable.favorite_icon_choosed)
        if (bool == false) this.setImageResource(R.drawable.favorite_icon)
    }
}

@BindingAdapter("loadImageFavourite", "posFavourite")
fun ImageView.loadImageFavourite(viewModel: HomeViewModel, pos:Int){

    var newMovies = viewModel.getImages().value?.let {
        var list = mutableListOf<MyImage>()
        for (i in it) if (i.isFavorite) list.add(i)
        list
    }

    if (newMovies != null) {
        for (i in newMovies){
            Log.d("TAG", i.name)
        }
    }

    Glide.with(this.context)
        .load(newMovies?.get(pos)?.image)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}
