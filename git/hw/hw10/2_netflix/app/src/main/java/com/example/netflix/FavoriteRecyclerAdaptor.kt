package com.example.netflix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.FavoriteRecyclerAdaptor.ViewHolder

class FavoriteRecyclerAdaptor(private var items:HomeViewModel, private val context: Context)
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

    override fun getItemCount(): Int {
        return items.getFavorites().size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text = items.getFavorites()[position].name

        Glide.with(context)
            .load(items.getFavorites()[position].image)
            .into(holder.imageView)

        holder.iconView.setImageResource(R.drawable.favorite_icon_choosed)
    }

}