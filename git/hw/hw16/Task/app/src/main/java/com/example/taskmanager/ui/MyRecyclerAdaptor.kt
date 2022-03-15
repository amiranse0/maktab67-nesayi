package com.example.taskmanager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.CustomViewBinding

class MyRecyclerAdaptor(private val items:List<Task>) : RecyclerView.Adapter<MyRecyclerAdaptor.MyViewHolder>() {

    inner class MyViewHolder(private val binding: CustomViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(position: Int){
                binding.timeDateCustomViewTv.text = items[position].time
                binding.titleCustomViewTv.text = items[position].title
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomViewBinding.inflate(inflater)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}