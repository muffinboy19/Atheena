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

    private val moodTextView: TextView by lazy {findViewById<TextView>(R.id.textViewDisplayMood)}
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
        }
    }

    private fun updateTextViewWithMoodName(moodName: String){
        moodTextView.text = moodName
    }
}