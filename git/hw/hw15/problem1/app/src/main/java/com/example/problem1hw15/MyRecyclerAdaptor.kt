package com.example.problem1hw15

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.problem1hw15.databinding.CustomViewBinding

class MyRecyclerAdaptor(private val items: List<Item>) :
    RecyclerView.Adapter<MyRecyclerAdaptor.MyViewHolder>() {

    lateinit var thisClickItem: ClickItem

    inner class MyViewHolder(private val binding: CustomViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(position: Int) {
            binding.item = items[position]
        }

        init {
            binding.root.setOnClickListener(this);
        }
        override fun onClick(p0: View?) {
            thisClickItem.click(adapterPosition, p0)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = CustomViewBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ClickItem {
        fun click(position: Int, view: View?)
    }

    fun setClickItem(clickItem: ClickItem) {
        this.thisClickItem = clickItem
    }
}