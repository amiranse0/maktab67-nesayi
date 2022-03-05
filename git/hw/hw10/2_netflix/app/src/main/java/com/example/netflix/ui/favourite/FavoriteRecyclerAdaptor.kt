package com.example.netflix.ui.favourite

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.MyMovie
import com.example.netflix.ui.favourite.FavoriteRecyclerAdaptor.ViewHolder
import com.example.netflix.databinding.CustomFavouriteBinding
import com.example.netflix.ui.home.HomeViewModel

class FavoriteRecyclerAdaptor(private val listFavourite:List<MyMovie> , val owner: LifecycleOwner)
    :RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val binding: CustomFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pos:Int) {
            binding.movie = listFavourite[pos]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = CustomFavouriteBinding.inflate(inflater, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listFavourite.size
    }
}