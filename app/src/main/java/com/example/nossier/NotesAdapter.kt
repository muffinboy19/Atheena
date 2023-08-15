package com.example.nossier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotesAdapter(private val notes: List<NoteEntity>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.noteTitle)
        val timestampTextView: TextView = itemView.findViewById(R.id.noteDatenNTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]

        holder.titleTextView.text = note.title
        holder.timestampTextView.text = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()).format(
            Date(note.timestamp)
        )
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
