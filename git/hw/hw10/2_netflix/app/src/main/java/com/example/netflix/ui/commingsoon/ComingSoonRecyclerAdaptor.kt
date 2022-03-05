package com.example.netflix.ui.commingsoon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.MyMovie
import com.example.netflix.databinding.CustomViewComingSoonBinding
import com.example.netflix.ui.home.HomeViewModel

class ComingSoonRecyclerAdaptor(private var listNew:List<MyMovie>, val owner: LifecycleOwner)
    :RecyclerView.Adapter<ComingSoonRecyclerAdaptor.ViewHolder>() {

    inner class ViewHolder(private val binding:CustomViewComingSoonBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.movie = listNew[position]
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomViewComingSoonBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listNew.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}