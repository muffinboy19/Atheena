package com.example.nossier
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class MoodAdapter(
    private val images: List<Int>,
    private val moodNames :List<String>,
    private val updateFunction : (String)-> Unit)
    : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    inner class MoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageMood: ImageView = itemView.findViewById(R.id.imageMood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.mood_layout, parent, false)
        return MoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        val imageResource = images[position]
        holder.imageMood.setImageResource(imageResource)

        holder.itemView.setOnClickListener{

            val moodName = moodNames[position]
            updateFunction(moodName)
        }
    }

    override fun getItemCount(): Int {
        return images.size
    }
}

