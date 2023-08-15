package com.example.nossier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.room.Room

class EnteringData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entering_data)

        val noteEditText = findViewById<EditText>(R.id.quicknotetextarea)
        val SaveButton = findViewById<ImageView>(R.id.SaveButton)
        SaveButton.setOnClickListener{
        val noteText = noteEditText.text.toString()
            val currentTimeMillis = System.currentTimeMillis()
            val noteEntity = NoteEntity(title = "Note Title", content = noteText, timestamp = currentTimeMillis)
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "notes_db").build()
            val noteDao = db.noteDao()
            noteDao.insert(noteEntity)
            noteEditText.text.clear()



            finish()
        }










    }
}