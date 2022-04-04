package com.example.taskmanager.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.CustomViewBinding


class MyRecyclerAdaptor(private val items: List<Task>) :
    RecyclerView.Adapter<MyRecyclerAdaptor.MyViewHolder>() {

    private lateinit var clickOnTask: ClickOnTask


    inner class MyViewHolder(private val binding: CustomViewBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(position: Int) {
            binding.item = items[position]
        }

        override fun onClick(p0: View?) {
            clickOnTask.clickOnTask(adapterPosition, p0)
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

    interface ClickOnTask {
        fun clickOnTask(position: Int, view: View?)
    }

    fun setToClickOnTask(clickOnTask: ClickOnTask){
        this.clickOnTask = clickOnTask
    }

}