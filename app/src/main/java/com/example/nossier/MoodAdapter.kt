package com.example.nossier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MoodAdapter(private val images: List<Int>) : RecyclerView.Adapter<MoodAdapter.ViewHolder>() {

    private var selectedItemPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    selectedItemPosition = position
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mood_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResId = images[position]
        holder.imageView.setImageResource(imageResId)
        holder.itemView.isActivated = position == selectedItemPosition
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
