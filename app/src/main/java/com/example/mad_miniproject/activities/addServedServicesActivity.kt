package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.BookingModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addServedServicesActivity : AppCompatActivity() {

    private lateinit var savebtn: Button

    private lateinit var dbRef: DatabaseReference
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_served_services)

        savebtn = findViewById(R.id.savebtn)

        dbRef = FirebaseDatabase.getInstance().getReference("Booking")

        savebtn.setOnClickListener {
            //saveData()
        }
    }

//    private fun saveData(){
//        //getting values
//        val bookingHours= addBhours.text.toString()
//        val bookingDate= addBDate.text.toString()
//        val bookingAddr= addBaddr.text.toString()
//        val bookingPhone= addBphone.text.toString()
//        val bookingSName= addBname.text.toString()
//
//
//        if(bookingHours.isEmpty()){
//            addBhours.error = "Please Enter Hours"
//        }
//        if(bookingDate.isEmpty()){
//            addBDate.error = "Please Enter Hours"
//        }
//        if(bookingAddr.isEmpty()){
//            addBaddr.error = "Please Enter Hours"
//        }
//        if(bookingPhone.isEmpty()){
//            addBphone.error = "Please Enter Hours"
//        }
//        if(bookingSName.isEmpty()){
//            addBname.error = "Please Enter Hours"
//        }
//
//        val bookingId = dbRef.push().key!!
//
//        val booking = BookingModel(bookingId, bookingHours, bookingDate, bookingAddr, bookingPhone, bookingSName)
//
//        dbRef.child(bookingId).setValue(booking)
//            .addOnCompleteListener{
//                Toast.makeText(this,"Data Inserted Successfully", Toast.LENGTH_LONG).show()
//
//                addBhours.text.clear()
//                addBDate.text.clear()
//                addBaddr.text.clear()
//                addBphone.text.clear()
//                addBname.text.clear()
//
//
//            }.addOnFailureListener { err ->
//                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
//            }

   // }
}