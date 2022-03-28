package com.example.fliker.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.fliker.R
import com.squareup.picasso.Picasso

class RecyclerAdaptor(private val images:List<String>, private val context:Context):
    RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>() {

    class ViewHolder(itemView: View, var imageView: ImageView = itemView.findViewById(R.id.iv))
        : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso
            .get()
            .load(images[position])
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
