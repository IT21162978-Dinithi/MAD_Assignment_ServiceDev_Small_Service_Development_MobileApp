package com.example.mad_miniproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BookingDetailedActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_detailed)

        var btnEditBook1=findViewById<Button>(R.id.btnEditBook1)
        btnEditBook1.setOnClickListener {
            val intentD4= Intent (this, editBookingActivity::class.java)
            startActivity(intentD4)

        }
    }
}