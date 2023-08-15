package com.example.nossier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import java.text.SimpleDateFormat
import java.util.Date
import com.google.firebase.database.FirebaseDatabase

class EnteringData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entering_data)
        FirebaseApp.initializeApp(this)
        val noteBoddy = findViewById<EditText>(R.id.EditTextViewBody)
        val noteTitle = findViewById<EditText>(R.id.EditTextViewTitle)
        val SaveButton = findViewById<ImageView>(R.id.SaveButton)
        val noteDage = findViewById<TextView>(R.id.TextViewDisplayDate)


        val formattedDate = intent.getLongExtra("selectedDate", -1)
        if (formattedDate != -1L) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")  // Choose your desired date format
            val selectedDate = Date(formattedDate)
            val formattedSelectedDate = dateFormat.format(selectedDate)
            noteDage.text = formattedSelectedDate
        }

        val  moodRecyclerView  = findViewById<RecyclerView>(R.id.moodrecylerView)
        moodRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val images = listOf(
            R.drawable.happt,
            R.drawable.joyfull,
            R.drawable.sad,
            R.drawable.normal,
            R.drawable.extreme_sad
        )
        val moodAdapter = MoodAdapter(images)


        SaveButton.setOnClickListener{
            val noteTitle = noteTitle.text.toString()
            val  notecontent = noteBoddy.text.toString()
            val currentTimeMillis = System.currentTimeMillis()
//            saveNoteToFirebase(noteTitle, noteContent)
//            saveNoteToLocalStorage(noteTitle, noteContent)
//            finish()
        }
    }



//    private fun saveNoteToFirebase(content: String) {
//        val database = FirebaseDatabase.getInstance().reference
//        val notesReference = database.child("notes")
//        val noteId = notesReference.push().key
//        val newNote = Note(noteId, content)
//        notesReference.child(noteId).setValue(newNote)
//    }
//
//    private fun saveNoteToLocalStorage(content: String) {
//        val noteId = generateNoteId() // Implement a method to generate a unique ID
//        val fileName = "note_$noteId.txt"
//        openFileOutput(fileName, Context.MODE_PRIVATE).use {
//            it.write(content.toByteArray())
//        }
//    }
}