package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R
import com.example.myapplication.models.paymentModel
import com.google.firebase.database.FirebaseDatabase

class viewPaymentDetailsActivity : AppCompatActivity() {

    private lateinit var tvpayId: TextView
    private lateinit var radioButton11: RadioButton
    private lateinit var numbertext: TextView
    private lateinit var datetext: TextView
    private lateinit var yeartext: TextView
    private lateinit var editTextNumber2: TextView
    private lateinit var btn_update: Button
    private lateinit var btn_delete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_payment_details)

        initView()
        setValuesToViews()

        btn_update.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("payId").toString(),
                intent.getStringExtra("crdNumber").toString()
            ) }

        btn_delete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("payId").toString(),
            )
        }
    }

    private fun initView() {
        tvpayId = findViewById(R.id.payId)
        radioButton11 = findViewById(R.id.radioButton11)
        numbertext = findViewById(R.id.numbertext)
        datetext = findViewById(R.id.datetext)
        yeartext = findViewById(R.id.yeartext)
        editTextNumber2 = findViewById(R.id.editTextNumber2)

        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
    }

    private fun setValuesToViews() {
        tvpayId.text = intent.getStringExtra("payId")
        numbertext.text = intent.getStringExtra("crdNumber")
        datetext.text = intent.getStringExtra("month")
        yeartext.text = intent.getStringExtra("year")
        editTextNumber2.text = intent.getStringExtra("cvn")


    }

    private fun openUpdateDialog(
        payId: String,
        crdNumber: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.updatepayment, null)

        mDialog.setView(mDialogView)

        val upcard = mDialogView.findViewById<EditText>(R.id.upcardno)
        val upmonth = mDialogView.findViewById<EditText>(R.id.upmonth)
        val upyear = mDialogView.findViewById<EditText>(R.id.upyear)
        val upcvn = mDialogView.findViewById<EditText>(R.id.updatecvn)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        upcard.setText(intent.getStringExtra("crdNumber").toString())
        upmonth.setText(intent.getStringExtra("month").toString())
        upyear.setText(intent.getStringExtra("year").toString())
        upcvn.setText(intent.getStringExtra("cvn").toString())

        mDialog.setTitle("Updating $crdNumber Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updatePayData(
                payId,
                upcard.text.toString(),
                upmonth.text.toString(),
                upyear.text.toString(),
                upcvn.text.toString()
            )
            Toast.makeText(applicationContext, "Payment Data Updated", Toast.LENGTH_LONG).show()
            //setting updated data to textviews
            numbertext.text = upcard.text.toString()
            datetext.text = upmonth.text.toString()
            yeartext.text = upyear.text.toString()
            editTextNumber2.text = upcvn.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun deleteRecord(
    id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Payment").child(id)
        val mTask = dbRef.removeValue()
        mTask.addOnSuccessListener {
            Toast.makeText(this, "Payment data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, frechPaymentActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
}
    private fun updatePayData(
        id: String,
        card: String,
        month: String, 
        year: String,
        cvn: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Payment").child(id)
        val empInfo = paymentModel(id, card, month, year, cvn)
        dbRef.setValue(empInfo)
    }
}