package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad_miniproject.R

class dashboardActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var bookLy=findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.bookLy)
        bookLy.setOnClickListener {
            val intentD5= Intent (this, frechBookingActivity::class.java)
            startActivity(intentD5)

        }

        var serviceLy=findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.serviceLy)
        serviceLy.setOnClickListener {
            val intentD8= Intent (this, allServicesRetrive::class.java)
            startActivity(intentD8)

        }

        var feedbackLy=findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.feedbackLy)
        feedbackLy.setOnClickListener {
            val intent= Intent (this, FeedbackDisplay::class.java)
            startActivity(intent)

        }


    }
}