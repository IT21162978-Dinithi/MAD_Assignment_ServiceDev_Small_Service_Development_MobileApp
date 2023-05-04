package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addPaymentDetailsActivity : AppCompatActivity() {


    private lateinit var radioButton9: RadioButton
    private lateinit var radioButton10: RadioButton
    private lateinit var crdnumber: EditText
    private lateinit var exmonth: EditText
    private lateinit var exyear: EditText
    private lateinit var cvn2: EditText
    private lateinit var btn_cancel:Button
    private lateinit var paybtn:Button

    private lateinit var dbref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment_details)

        radioButton9 =findViewById(R.id.radioButton9)
        radioButton10 =findViewById(R.id.radioButton10)
        crdnumber =findViewById(R.id.crdnumber)
        exmonth =findViewById(R.id.exmonth)
        exyear=findViewById(R.id.exyear)
        cvn2 =findViewById(R.id.cvn2)
        btn_cancel =findViewById(R.id.btn_cancel)
        paybtn =findViewById(R.id.paybtn)

        dbref =FirebaseDatabase.getInstance().getReference( "PaymentDetails ")

        paybtn.setOnClickListener{
            savePaymentData()
        }


        var btn_cancel = findViewById<Button>(R.id.btn_cancel)
        btn_cancel.setOnClickListener {
            val intentP2 = Intent(this, MainActivity::class.java)
            startActivity(intentP2)
        }
        var bookingBtn = findViewById<Button>(R.id.paybtn)
        bookingBtn.setOnClickListener {
            val intentP4 = Intent(this, paymentSuccessActivity::class.java)
            startActivity(intentP4)
        }
    }
    private fun savePaymentData() {
        //getting values

        val crdNumber = crdnumber.text.toString()
        val month = exmonth.text.toString()
        val year = exyear.text.toString()
        val cvn = cvn2.text.toString()

        //check if the form is empty
        if (crdNumber.isEmpty()) {
            crdnumber.error = "Please fill this field"
        }
        if (month.isEmpty()){
            exmonth.error = "Please fill this field"
        }
        if (year.isEmpty()){
            exyear.error = "Please fill this field"
        }
        if (cvn.isEmpty()){
            cvn2.error = "Please fill this field"
        }

        //generate unique ID s
        val payId = dbref.push().key!!

        val PaymentDetails =paymentModel(payId,crdNumber, month, year, cvn)

            dbref.child(payId).setValue(PaymentDetails)
                .addOnCompleteListener{
                    Toast.makeText( this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                    crdnumber.text.clear()
                    exmonth.text.clear()
                    exyear.text.clear()
                    cvn2.text.clear()


                }.addOnFailureListener{err->
                    Toast.makeText( this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

    }

}