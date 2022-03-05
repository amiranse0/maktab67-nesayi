package com.example.netflix

import android.content.Intent
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.netflix.ui.home.HomeViewModel

@BindingAdapter("loadImage")
fun ImageView.loadImage(image:String?){

    image?.let { Log.d("TAG", it) }

    Glide.with(this.context)
        .load(image)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}

@BindingAdapter("onClickFavourite","pos")
fun ImageView.onClickFavourite(listMovies: List<MyMovie>, pos: Int){

    if (listMovies[pos].isFavorite) this.setImageResource(R.drawable.favorite_icon_choosed)
    else this.setImageResource(R.drawable.favorite_icon)

//    this.setOnClickListener {
//        val bool = viewModel.clickFavourite(pos)
//        if (bool == true) this.setImageResource(R.drawable.favorite_icon_choosed)
//        if (bool == false) this.setImageResource(R.drawable.favorite_icon)
//    }
}

@BindingAdapter("onClickShare")
fun ImageView.onClickShare(name:String?){
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra("share", name)

        val chooser = Intent.createChooser(intent, "Share this")
        context.startActivity(chooser)
    }
}
