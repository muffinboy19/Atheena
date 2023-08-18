package com.example.nossier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth

// 1

class GettinStarted : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gettin_started)
        val gets = findViewById<AppCompatButton>(R.id.getstarted)
        mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            // User is authenticated, navigate to Homescreen activity
            val intent = Intent(this, Homescreen::class.java)
            startActivity(intent)
        }


        gets.setOnClickListener{
            val intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()


        }



    }
}