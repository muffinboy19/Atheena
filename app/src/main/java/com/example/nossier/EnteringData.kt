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
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat

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
        
        val boldButton = findViewById<Button>(R.id.bold)
        val italicButton = findViewById<Button>(R.id.italics)
        val underlineButton = findViewById<ImageButton>(R.id.underlineButton)
        val colorButton = findViewById<ImageButton>(R.id.colorButton)

        boldButton.setOnClickListener { applyStyleSpan(StyleSpan(Typeface.BOLD)) }
        italicButton.setOnClickListener { applyStyleSpan(StyleSpan(Typeface.ITALIC)) }
     //   underlineButton.setOnClickListener { applyUnderlineSpan(UnderlineSpan()) }
        colorButton.setOnClickListener { showColorPickerDialog() }


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
            SaveButton.setOnClickListener {
                val userId = mAuth.currentUser?.uid
                if (userId != null) {
                    val title = noteTitle.text.toString()
                    val body = noteBoddy.text.toString()
                    val date = noteDage.text.toString()
                    val mood = moodTextView.text.toString()

                    val note = Note(title, body, date, mood)

                    val userNotesRef = database.child("users").child(userId).child("notes")
                    val newNoteRef = userNotesRef.push()
                    newNoteRef.setValue(note).addOnCompleteListener { noteCreationTask ->
                        if (noteCreationTask.isSuccessful) {
                            Toast.makeText(this, "Note saved successfully", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Failed to save note", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }


        }
    }

    private fun applyStyleSpan(span: CharacterStyle) {
        applySpan(span)
    }

    private fun applyUnderlineSpan(span: CharacterStyle) {
        applySpan(span)
    }

    private fun applyForegroundColorSpan(span: CharacterStyle) {
        applySpan(span)
    }
    private fun applySpan(span: CharacterStyle) {
        val startIndex = noteBoddy.selectionStart
        val endIndex = noteBoddy.selectionEnd

        if (startIndex != endIndex) {
            val selectedText = noteBoddy.text.subSequence(startIndex, endIndex)
            val spannable = SpannableStringBuilder(noteBoddy.text)
            spannable.setSpan(span, startIndex, endIndex, SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE)
            noteBoddy.text = spannable
        }
    }

    private fun showColorPickerDialog() {
        // Implement a color picker dialog to allow users to choose text color.
        // You can use third-party libraries or Android's ColorPickerDialog.
        // Once a color is selected, call applyForegroundColorSpan with the chosen color.
        val selectedColor = ContextCompat.getColor(this, R.color.app_clor) // Replace with the selected color
        val colorSpan = ForegroundColorSpan(selectedColor)
        applyForegroundColorSpan(colorSpan)
    }

    private fun updateTextViewWithMoodName(moodName: String){
        moodTextView.text = moodName
    }
}