package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddServices : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_services)

        val cancel001=findViewById<Button>(R.id.cancel001)
        cancel001.setOnClickListener {
            val intentS2= Intent (this, MainActivity::class.java)
            startActivity(intentS2)

        }

        val add001=findViewById<Button>(R.id.add001)
        add001.setOnClickListener {
            val intentS3= Intent (this, MainActivity::class.java)
            startActivity(intentS3)

        }
    }
}