package com.example.quizretrofit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fliker.R

class RecyclerAdaptor(private val image:List<ImageProperties>, private val context:Context):
    RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>() {

    class ViewHolder(itemView: View, var imageView: ImageView = itemView.findViewById(R.id.iv))
        : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(image[position].url_s)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        Log.d("TAG", image.size.toString())
        return image.size
    }
}
