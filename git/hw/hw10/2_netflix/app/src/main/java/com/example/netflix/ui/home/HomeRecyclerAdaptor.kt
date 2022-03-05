package com.example.netflix.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
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

    inner class ViewHolder(private val binding: CustomeViewHomeBinding):
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.lifecycleOwner = owner
            binding.listMovies = listMovies
        }

        fun bind(position: Int) {
            binding.pos = position
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
}