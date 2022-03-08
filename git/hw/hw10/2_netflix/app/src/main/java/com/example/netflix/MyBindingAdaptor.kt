package com.example.netflix

import android.content.Intent
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun ImageView.loadImage(image:String?){

    image?.let { Log.d("TAG", it) }

    Glide.with(this.context)
        .load(image)
        .placeholder(R.drawable.ic_baseline_image_24)
        .error(R.drawable.ic_baseline_broken_image_24)
        .into(this)
}

@BindingAdapter("onClickShare")
fun ImageView.onClickShare(name:String?){
    this.setOnClickListener {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, name as String)

        Log.d("THIS", name as String)

        val chooser = Intent.createChooser(intent, "Share this")
        context.startActivity(chooser)
    }
}
