package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mad_miniproject.models.BookingModel
import com.example.mad_miniproject.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addBookingActivity : AppCompatActivity() {


    private lateinit var addBhours: EditText
    private lateinit var addBDate: EditText
    private lateinit var addBaddr: EditText
    private lateinit var addBphone: EditText
    private lateinit var addBname: EditText
    private lateinit var addBmail: EditText
    private lateinit var bookingBtn: Button

    private lateinit var dbRef: DatabaseReference



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_booking)



        addBhours = findViewById(R.id.etBhours)
        addBDate = findViewById(R.id.etBDate)
        addBaddr = findViewById(R.id.etBaddr)
        addBphone = findViewById(R.id.etBphone)
        addBname = findViewById(R.id.addBname)

        bookingBtn = findViewById(R.id.bookingBtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Booking")

        bookingBtn.setOnClickListener {
            saveBookingData()
        }

        var btncancel1=findViewById<Button>(R.id.btncancel1)
        btncancel1.setOnClickListener {
            val intentD2= Intent (this, MainActivity::class.java)
            startActivity(intentD2)

        }

    }

    private fun saveBookingData(){
        //getting values
        val bookingHours= addBhours.text.toString()
        val bookingDate= addBDate.text.toString()
        val bookingAddr= addBaddr.text.toString()
        val bookingPhone= addBphone.text.toString()
        val bookingSName= addBname.text.toString()


        if(bookingHours.isEmpty()){
            addBhours.error = "Please Enter Hours"
        }
        if(bookingDate.isEmpty()){
            addBDate.error = "Please Enter Hours"
        }
        if(bookingAddr.isEmpty()){
            addBaddr.error = "Please Enter Hours"
        }
        if(bookingPhone.isEmpty()){
            addBphone.error = "Please Enter Hours"
        }
        if(bookingSName.isEmpty()){
            addBname.error = "Please Enter Hours"
        }

        val bookingId = dbRef.push().key!!

        val booking = BookingModel(bookingId, bookingHours, bookingDate, bookingAddr, bookingPhone, bookingSName)

        dbRef.child(bookingId).setValue(booking)
            .addOnCompleteListener{
                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()

                addBhours.text.clear()
                addBDate.text.clear()
                addBaddr.text.clear()
                addBphone.text.clear()
                addBname.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }




}