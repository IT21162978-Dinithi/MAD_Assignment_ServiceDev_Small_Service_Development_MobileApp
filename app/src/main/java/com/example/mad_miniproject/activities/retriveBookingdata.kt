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

    lateinit var btnbook1 : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrive_bookingdata)

        var bookbtn=findViewById<Button>(R.id.btnadd0)
        bookbtn.setOnClickListener {
            val intent= Intent (this, addBookingActivity::class.java)
            startActivity(intent)

        }

        var cancelbtn=findViewById<Button>(R.id.btncan0)
        cancelbtn.setOnClickListener {
            val intent= Intent (this, allServicesRetrive::class.java)
            startActivity(intent)

        }



        initView()
        setValuesToViews()

//        btnbook1 = findViewById(R.id.btnbook1)
//
//        btnbook1.setOnClickListener {
//
//            var i = Intent(this,addBookingActivity::class.java)
//            startActivity(i)
//            //finish()
//
//
//        }

//        var btnbook1=findViewById<Button>(R.id.btnbook1)
//        btnbook1.setOnClickListener {
//            val intentD1= Intent (this, addBookingActivity::class.java)
//            startActivity(intentD1)
//
//        }

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