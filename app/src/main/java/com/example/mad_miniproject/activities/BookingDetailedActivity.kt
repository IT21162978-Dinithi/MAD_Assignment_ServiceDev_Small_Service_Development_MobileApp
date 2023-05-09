package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.BookingModel
import com.google.firebase.database.FirebaseDatabase

class BookingDetailedActivity : AppCompatActivity() {



    private lateinit var rBId: TextView
    private lateinit var rBhours: TextView
    private lateinit var rBDate: TextView
    private lateinit var rBaddr: TextView
    private lateinit var rBphone: TextView
    private lateinit var addBname: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_detailed)

        initView()
        setValuesToViews() //display value

        //update
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("bookingId").toString(),
                intent.getStringExtra("bookingSName").toString()
            )
        }

        //delete
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("bookingId").toString()
            )
        }

        //payment
        var paybtn=findViewById<Button>(R.id.btnpay001)
        paybtn.setOnClickListener {
            val intent= Intent (this, calcServiceChargeActivity::class.java)
            startActivity(intent)

        }
    }


    private fun initView(){




        rBId = findViewById(R.id.rBId)
        rBhours = findViewById(R.id.rBhours)
        rBDate = findViewById(R.id.rBDate)
        rBaddr = findViewById(R.id.rBaddr)
        rBphone = findViewById(R.id.rBphone)
        addBname = findViewById(R.id.addBname)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }

    //display value function
    private fun setValuesToViews() {



        rBId.text = intent.getStringExtra("bookingId")
        rBhours.text = intent.getStringExtra("bookingHours")
        rBDate.text = intent.getStringExtra("bookingDate")
        rBaddr.text = intent.getStringExtra("bookingAddr")
        rBphone.text = intent.getStringExtra("bookingPhone")
        addBname.text = intent.getStringExtra("bookingSName")

    }


    //Update dialogbox function
    private fun openUpdateDialog(
        bookingId: String,
        bookingSName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_edit_booking,null)

        mDialog.setView(mDialogView)

        val etBhours = mDialogView.findViewById<EditText>(R.id.etBhours)
        val etBDate = mDialogView.findViewById<EditText>(R.id.etBDate)
        val etBaddr = mDialogView.findViewById<EditText>(R.id.etBaddr)
        val etBphone = mDialogView.findViewById<EditText>(R.id.etBphone)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etBhours.setText(intent.getStringExtra("bookingHours").toString())
        etBDate.setText(intent.getStringExtra("bookingDate").toString())
        etBaddr.setText(intent.getStringExtra("bookingAddr").toString())
        etBphone.setText(intent.getStringExtra("bookingPhone").toString())

        mDialog.setTitle("Updating $bookingId Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateBookingData(
                bookingId,
                etBhours.text.toString(),
                etBDate.text.toString(),
                etBaddr.text.toString(),
                etBphone.text.toString(),
                bookingSName
            )

            Toast.makeText(applicationContext, "Update Booking Data",Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            rBhours.text = etBhours.text.toString()
            rBDate.text = etBDate.text.toString()
            rBaddr.text = etBaddr.text.toString()
            rBphone.text = etBphone.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateBookingData(
        id:String,
        hours:String,
        date:String,
        address:String,
        phone:String,
        name: String

    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Booking").child(id)
        val bookingInfo = BookingModel(id,hours, date, address ,phone , name)
        dbRef.setValue(bookingInfo)
    }

    //delete record
    private fun deleteRecord(
        bookingId: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Booking").child(bookingId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Booking data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, frechBookingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}