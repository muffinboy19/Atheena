package com.example.nossier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton

class EnteringData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entering_data)

        val SaveButton = findViewById<AppCompatButton>(R.id.SaveButton)
        SaveButton.setOnClickListener{
            finish()
        }










    }
}