package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.models.paymentModel
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

    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment_details)

        radioButton9 =findViewById(R.id.radioButton11)
        radioButton10 =findViewById(R.id.radioButton10)
        crdnumber =findViewById(R.id.crdnumber)
        exmonth =findViewById(R.id.exmonth)
        exyear=findViewById(R.id.exyear)
        cvn2 =findViewById(R.id.cvn2)
        btn_cancel =findViewById(R.id.btn_cancel)
        paybtn =findViewById(R.id.paybtn)

        dbRef =FirebaseDatabase.getInstance().getReference( "Payment")

        paybtn.setOnClickListener{
            savePaymentData()
        }


        var btn_cancel = findViewById<Button>(R.id.btn_cancel)
        btn_cancel.setOnClickListener {
            val intentP2 = Intent(this, MainActivity::class.java)
            startActivity(intentP2)
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
        val payId = dbRef.push().key!!

        val payment = paymentModel(payId,crdNumber, month, year, cvn)

         dbRef.child(payId).setValue(payment)
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