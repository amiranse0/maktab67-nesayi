package com.example.netflix

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ComingSoonRecyclerAdaptor(private var items:ComingSoonViewModel, private val context: Context)
    :RecyclerView.Adapter<ComingSoonRecyclerAdaptor.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var titleTextView = itemView.findViewById<TextView>(R.id.custom_title_coming_soon)
        var descriptionTextView = itemView.findViewById<TextView>(R.id.custom_description_coming_soon)
        var posterImageView = itemView.findViewById<ImageView>(R.id.custom_iv_coming_soon)
        var iconImageView = itemView.findViewById<ImageView>(R.id.custom_icon_coming_soon)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.custom_view_coming_soon, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.listNewMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(items.listNewMovies[position].poster)
            .placeholder(R.drawable.image_icon)
            .into(holder.posterImageView)

        holder.titleTextView.text = items.listNewMovies[position].title
        holder.descriptionTextView.text = items.listNewMovies[position].description

        holder.iconImageView.setImageResource(R.drawable.share_icon)

        holder.iconImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra("share", items.comingSoonMovies[position].third)

            val chooser = Intent.createChooser(intent, "Share this")
            context.startActivity(chooser)
        }
    }
}