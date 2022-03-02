package com.example.netflix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.FavoriteRecyclerAdaptor.ViewHolder
import com.example.netflix.databinding.CustomFavouriteBinding
import com.example.netflix.databinding.CustomeViewHomeBinding

class FavoriteRecyclerAdaptor(private var viewModel:HomeViewModel, private val context: Context)
    :RecyclerView.Adapter<ViewHolder>() {

    inner class ViewHolder(private val binding: CustomFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newViewModel: HomeViewModel, pos:Int) {
            binding.viewModel = newViewModel
            binding.pos = pos
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = CustomFavouriteBinding.inflate(inflater, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel,position)
    }

    override fun getItemCount(): Int {
        var count = 0
        for(i in viewModel.getImages().value!!){
            if (i.isFavorite) count++
        }
        return count
    }
}