package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.ServicesModel


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddServices : AppCompatActivity() {

    private lateinit var AddServices: EditText
    private lateinit var AddNIC: EditText
    private lateinit var AddAge: EditText
    private lateinit var AddGender: EditText
    private lateinit var AddAddress: EditText
    private lateinit var AddHour: EditText
    private lateinit var AddAvailable: EditText
    private lateinit var AddName: EditText
    private lateinit var btnSaveData : Button

    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_services)

        var addsernw=findViewById<Button>(R.id.btncan1)
        addsernw.setOnClickListener {
            val intent= Intent (this, FetchingServices::class.java)
            startActivity(intent)

        }

        AddServices = findViewById(R.id.addservicetitle)
        AddNIC = findViewById(R.id.addnic)
        AddAge = findViewById(R.id.addaddr)
        AddGender = findViewById(R.id.addage)
        AddAddress = findViewById(R.id.addaddress)
        AddHour = findViewById(R.id.addhour)
        AddAvailable = findViewById(R.id.addavailable)
        AddName = findViewById(R.id.addd)

        btnSaveData = findViewById(R.id.btnUpdate)

        dbRef = FirebaseDatabase.getInstance().getReference("Services")

        btnSaveData.setOnClickListener {
            saveServicesData()
        }
}

private fun saveServicesData() {

    //getting values
    val addservices = AddServices.text.toString()
    val addnic = AddNIC.text.toString()
    val addage = AddAge.text.toString()
    val addgender = AddGender.text.toString()
    val addaddress = AddAddress.text.toString()
    val addhour = AddHour.text.toString()
    val addavailable = AddAvailable.text.toString()
    val addname = AddName.text.toString()

    if (addservices.isEmpty()) {
        AddServices.error = "Please enter Service"
    }
    if (addnic.isEmpty()) {
        AddNIC.error = "Please enter NIC"
    }
    if (addage.isEmpty()) {
        AddAge.error = "Please enter age"
    }
    if (addgender.isEmpty()) {
        AddGender.error = "Please enter gender"
    }
    if (addaddress.isEmpty()) {
        AddAddress.error = "Please enter address"
    }
    if (addhour.isEmpty()) {
        AddHour.error = "Please enter hourly rate"
    }
    if (addavailable.isEmpty()) {
        AddAvailable.error = "Please enter availability"
    }
    if (addname.isEmpty()) {
        AddName.error = "Please enter name"
    }

    val serviceID = dbRef.push().key!!

    val service = ServicesModel(serviceID, addservices, addnic, addage, addgender, addaddress, addhour, addavailable, addname)

    dbRef.child(serviceID).setValue(service)
        .addOnCompleteListener {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

            AddServices.text.clear()
            AddNIC.text.clear()
            AddAge.text.clear()
            AddGender.text.clear()
            AddAddress.text.clear()
            AddHour.text.clear()
            AddAvailable.text.clear()
            AddName.text.clear()

        }.addOnFailureListener { err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }

}
}