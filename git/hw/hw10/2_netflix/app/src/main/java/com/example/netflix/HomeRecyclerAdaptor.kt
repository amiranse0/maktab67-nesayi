package com.example.netflix

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.HomeRecyclerAdaptor.ViewHolder

class HomeRecyclerAdaptor(private var items:HomeViewModel, private val context: Context)
    :RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.findViewById<ImageView>(R.id.custom_iv)
        var textView = itemView.findViewById<TextView>(R.id.custom_tv)
        var iconView = itemView.findViewById<ImageView>(R.id.custom_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custome_view_home, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items.images[position].name

        Glide.with(context)
            .load(items.images[position].image)
            .into(holder.imageView)

        if (items.images[position].isFavorite) holder.iconView.setImageResource(R.drawable.favorite_icon_choosed)
        else holder.iconView.setImageResource((R.drawable.favorite_icon))

        holder.iconView.setOnClickListener {
            if (items.images[position].isFavorite) {
                (it as ImageView).setImageResource(R.drawable.favorite_icon)
                items.removeFromFavorite(position)

                Log.d("TAG", items.images[position].isFavorite.toString())
            }
            else {
                (it as ImageView).setImageResource(R.drawable.favorite_icon_choosed)
                items.addToFavorite(position)

                Log.d("TAG", items.images[position].isFavorite.toString())
            }
        }

    }

    override fun getItemCount(): Int {
        return items.getSize()
    }
}