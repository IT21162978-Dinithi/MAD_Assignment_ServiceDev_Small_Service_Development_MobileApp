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
import com.example.mad_miniproject.models.ServicesModel
import com.google.firebase.database.FirebaseDatabase


class ServiceDetails : AppCompatActivity() {


    private lateinit var id: TextView
    private lateinit var addservicetitle: TextView
    private lateinit var addnic: TextView
    private lateinit var addage: TextView
    private lateinit var addgen: TextView
    private lateinit var addaddr: TextView
    private lateinit var addhour: TextView
    private lateinit var addd: TextView
    private lateinit var name: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("serviceID").toString(),
                intent.getStringExtra("addservices").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("serviceID").toString()
            )
        }

    }

    private fun initView() {
        id = findViewById(R.id.id)
        addservicetitle = findViewById(R.id.addservicetitle)
        addnic = findViewById(R.id.addnic)
        addage = findViewById(R.id.addage)
        addgen = findViewById(R.id.addgen)
        addaddr = findViewById(R.id.addaddr)
        addhour = findViewById(R.id.addhour)
        addd = findViewById(R.id.addd)
        name = findViewById(R.id.name)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btncan1)
    }

    private fun setValuesToViews() {

        id.text = intent.getStringExtra("serviceID")
        addservicetitle.text = intent.getStringExtra("addservices")
        addnic.text = intent.getStringExtra("addnic")
        addage.text = intent.getStringExtra("addage")
        addgen.text = intent.getStringExtra("addgender")
        addaddr.text = intent.getStringExtra("addaddress")
        addhour.text = intent.getStringExtra("addhour")
        addd.text = intent.getStringExtra("addavailable")
        name.text = intent.getStringExtra("adddescription")




    }


    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Services").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Service data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingServices::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun openUpdateDialog(
        serviceID: String,
        addservices: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etSevti = mDialogView.findViewById<EditText>(R.id.etSevti)
        val etSevNic = mDialogView.findViewById<EditText>(R.id.etSevNic)
        val etSevAge = mDialogView.findViewById<EditText>(R.id.etSevAge)
        val etSevGen = mDialogView.findViewById<EditText>(R.id.etSevGen)

        val etSevAddr = mDialogView.findViewById<EditText>(R.id.etSevAddr)
        val etHourAge = mDialogView.findViewById<EditText>(R.id.etHourAge)
        val etAva = mDialogView.findViewById<EditText>(R.id.etAva)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)


        etSevti.setText(intent.getStringExtra("addservices").toString())
        etSevNic.setText(intent.getStringExtra("addnic").toString())
        etSevAge.setText(intent.getStringExtra("addage").toString())
        etSevGen.setText(intent.getStringExtra("addgender").toString())

        etSevAddr.setText(intent.getStringExtra("addaddress").toString())
        etHourAge.setText(intent.getStringExtra("addhour").toString())
        etAva.setText(intent.getStringExtra("addavailable").toString())


        mDialog.setTitle("Updating $addservices Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateSevData(
                serviceID,
                etSevti.text.toString(),
                etSevNic.text.toString(),
                etSevAge.text.toString(),
                etSevGen.text.toString(),


                etSevAddr.text.toString(),
                etHourAge.text.toString(),
                etAva.text.toString()


            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews

            addservicetitle.text = etSevti.text.toString()
            addnic.text = etSevNic.text.toString()
            addage.text = etSevAge.text.toString()
            addgen.text = etSevGen.text.toString()

            addaddr.text = etSevAddr.text.toString()
            addhour.text = etHourAge.text.toString()
            addd.text = etAva.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateSevData(
        id: String,
        title: String,
        nic: String,
        age: String,
        gen: String,

        addr: String,
        hr: String,
        ava: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Services").child(id)
        val sevInfo = ServicesModel(id, title,nic,age,gen,addr, hr, ava)
        dbRef.setValue(sevInfo)
    }

}