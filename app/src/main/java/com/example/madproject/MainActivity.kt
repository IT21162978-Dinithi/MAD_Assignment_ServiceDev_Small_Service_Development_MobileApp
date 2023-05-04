package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val but001=findViewById<Button>(R.id.but001)
        but001.setOnClickListener {
            val intentS1= Intent (this, AddServices::class.java)
            startActivity(intentS1)

        }
    }
}