package com.example.nossier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class GettinStarted : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gettin_started)
        val gets = findViewById<AppCompatButton>(R.id.getstarted)
        gets.setOnClickListener{
            val intent = Intent(this,ContinueWithGoogle::class.java)
            startActivity(intent)
            finish()
        }
    }
}