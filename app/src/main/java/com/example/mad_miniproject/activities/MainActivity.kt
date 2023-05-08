package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mad_miniproject.R

class MainActivity : AppCompatActivity() {

    lateinit var recyclerbtn : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerbtn = findViewById(R.id.dash)

        recyclerbtn.setOnClickListener {

            var i = Intent(this,allServicesRetrive::class.java)
            startActivity(i)
            finish()


        }

        var btngo=findViewById<Button>(R.id.btngo)
        btngo.setOnClickListener {
            val intentD1= Intent (this, addBookingActivity::class.java)
            startActivity(intentD1)

        }

//        var dash=findViewById<Button>(R.id.dash)
//        dash.setOnClickListener {
//            val intentD6= Intent (this, allServicesRetrive::class.java)
//            startActivity(intentD6)
//
//        }




        var startbtn=findViewById<Button>(R.id.startbtn)
        startbtn.setOnClickListener {
            val intent= Intent (this, registerActivity::class.java)
            startActivity(intent)

        }

        var btnsignin=findViewById<Button>(R.id.btnsignin)
        btnsignin.setOnClickListener {
            val intent= Intent (this, signInActivity::class.java)
            startActivity(intent)

        }

    }
}