package com.example.mad_miniproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btngo=findViewById<Button>(R.id.btngo)
        btngo.setOnClickListener {
            val intentD1= Intent (this, addBookingActivity::class.java)
            startActivity(intentD1)

        }

    }
}