package com.example.picsum

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage", requireAll = true)
fun ImageView.loadImage(url:String){
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}