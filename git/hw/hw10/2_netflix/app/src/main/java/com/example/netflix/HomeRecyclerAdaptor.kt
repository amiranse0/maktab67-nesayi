package com.example.netflix

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.netflix.HomeRecyclerAdaptor.ViewHolder
import com.example.netflix.databinding.CustomeViewHomeBinding

class HomeRecyclerAdaptor(
    private var viewModel: HomeViewModel,
    var items:MutableList<MyImage>,
    private val context: Context,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list:List<MyImage>){
        items.clear()

        items.addAll(list)
        notifyDataSetChanged()
    }

    init {
        viewModel.getImages().observe(lifecycleOwner) {it1->
            Log.d("TAG", "this is in the init: ${it1?.size.toString()}")
            it1?.let {
                updateList(it)
            }
        }
    }

    inner class ViewHolder(private val binding: CustomeViewHomeBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newViewModel: HomeViewModel, position: Int) {
            binding.viewModel = newViewModel
            binding.pos = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomeViewHomeBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}