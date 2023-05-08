package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.mad_miniproject.R

class retriveBookingdata : AppCompatActivity() {

    private lateinit var rtitle: TextView
    private lateinit var rname: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrive_bookingdata)

        initView()
        setValuesToViews()

    }

    private fun initView(){

        rtitle = findViewById(R.id.rtitle)
        rname = findViewById(R.id.rname)

    }

    //display value function
    private fun setValuesToViews() {

        rtitle.text = intent.getStringExtra("addservices")
        rname.text = intent.getStringExtra("adddescription")



    }
}