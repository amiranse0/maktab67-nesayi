package com.example.netflix.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.MyMovie
import com.example.netflix.ui.home.HomeRecyclerAdaptor.ViewHolder
import com.example.netflix.databinding.CustomeViewHomeBinding

class HomeRecyclerAdaptor(
    private var listMovies: List<MyMovie>,
    private val owner: LifecycleOwner
) : RecyclerView.Adapter<ViewHolder>() {

    lateinit var binding: CustomeViewHomeBinding
    lateinit var clickFavourite: ClickFavourite

    inner class ViewHolder(private val binding: CustomeViewHomeBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.customIcon.setOnClickListener(this)
        }
        fun bind(position: Int) {
            binding.movie = listMovies[position]
        }

        override fun onClick(p0: View?) {
            clickFavourite.iconClicked(adapterPosition, p0 as ImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomeViewHomeBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    interface ClickFavourite{
        fun iconClicked(pos:Int, icon:ImageView)
    }

    fun setItemClickFavourite(clickFavourite: ClickFavourite){
        this.clickFavourite = clickFavourite
    }
}