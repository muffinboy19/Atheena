package com.example.nossier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotesAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val notetitle: TextView = itemView.findViewById(R.id.titleview)
        private val noteboddy: TextView = itemView.findViewById(R.id.boddyView)
        private val notemood: TextView = itemView.findViewById(R.id.moddview)
        private val notedate: TextView = itemView.findViewById(R.id.dateview)

        fun bind(note: Note) {
            notetitle.text = note.title
            noteboddy.text = note.body
            notemood.text = note.mood
            notedate.text = note.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}
