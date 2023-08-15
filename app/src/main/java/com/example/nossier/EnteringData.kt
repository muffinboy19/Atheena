package com.example.nossier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import java.text.SimpleDateFormat
import java.util.Date
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth


class EnteringData : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private val moodTextView: TextView by lazy {findViewById<TextView>(R.id.textViewDisplayMood)}

    private lateinit var noteTitle: EditText
    private lateinit var noteBoddy: EditText
    private lateinit var noteDage: TextView
    private lateinit var SaveButton: ImageView
    private val database = FirebaseDatabase.getInstance().reference.child("notes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entering_data)


        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()

         noteBoddy = findViewById<EditText>(R.id.EditTextViewBody)
         noteTitle = findViewById<EditText>(R.id.EditTextViewTitle)
         SaveButton = findViewById<ImageView>(R.id.SaveButton)
         noteDage = findViewById<TextView>(R.id.TextViewDisplayDate)


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
            R.drawable.joyfull,
            R.drawable.happt,
            R.drawable.normal,
            R.drawable.sad,
            R.drawable.extreme_sad
        )

        val mooodNames = listOf(
            "Joyfull",
            "Happy",
            "Normal",
            "Sad",
            "Worst"
        )
        val moodAdapter = MoodAdapter(images, mooodNames) { moodName ->
            updateTextViewWithMoodName(moodName)
        }
        moodRecyclerView.adapter = moodAdapter


        SaveButton.setOnClickListener{

            val uid = FirebaseAuth.getInstance().currentUser?.uid
            val database = FirebaseDatabase.getInstance()
            val userNotesRef = uid?.let { it1 -> database.reference.child("users").child(it1).child("notes") }

            val noteId = userNotesRef?.push()?.key
            val noteData = Note(noteTitle.text.toString(), noteBoddy.text.toString(), moodTextView.text.toString(), noteDage.text.toString())
            if (noteId != null) {
                userNotesRef.child(noteId).setValue(noteData)
            }
        }
    }


//    private fun saveNote() {
//
//    }
    private fun updateTextViewWithMoodName(moodName: String){
        moodTextView.text = moodName
    }
}