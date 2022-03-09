package com.example.problem1hw15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.problem1hw15.databinding.CustomViewBinding

class FavoriteRecyclerAdaptor(private val items: List<Item>) :
    RecyclerView.Adapter<FavoriteRecyclerAdaptor.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(private val binding: CustomViewBinding) :
        RecyclerView.ViewHolder(binding.root){

            fun bind(position: Int){
                binding.item = items[position]
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteRecyclerAdaptor.FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomViewBinding.inflate(inflater, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteRecyclerAdaptor.FavoriteViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = items.size

}