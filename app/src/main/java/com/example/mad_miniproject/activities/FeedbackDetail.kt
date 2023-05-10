package com.example.mad_miniproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.FeedbackModel
import com.example.mad_miniproject.activities.FeedbackDisplay


import com.google.firebase.database.FirebaseDatabase

class FeedbackDetail : AppCompatActivity() {

    //initialize varialbles
    private lateinit var tvServiceTitle : TextView
    private lateinit var tvProviderName : TextView
    private lateinit var tvFeedback : TextView
    private lateinit var tvAbout : TextView
    private lateinit var btnupdate : Button
    private lateinit var btndel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_detailed)

        initView()
        setValuesToViews()

        btnupdate.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("feedId").toString(),
                intent.getStringExtra("providerName").toString()

            )
        }

        btndel.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("feedId").toString()
            )
        }

    }

    private fun initView() {
        tvServiceTitle = findViewById(R.id.reTitle1)
        tvProviderName = findViewById(R.id.reSname)
        tvFeedback = findViewById(R.id.refee)
        tvAbout = findViewById(R.id.reidea)

        btnupdate = findViewById(R.id.button)
        btndel = findViewById(R.id.button2)
    }

    private fun setValuesToViews() {
        tvServiceTitle.text = intent.getStringExtra("serviceTitle")
        tvProviderName.text = intent.getStringExtra("providerName")
        tvFeedback.text = intent.getStringExtra("feedback")
        tvAbout.text = intent.getStringExtra("about")

    }

    private fun openUpdateDialog(
        feedId: String,
        providerName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_feedback_update,null)

        mDialog.setView(mDialogView)

        //initialize views
        val etServiceTitle = mDialogView.findViewById<EditText>(R.id.etServiceTitle)
        val etProviderName = mDialogView.findViewById<EditText>(R.id.etFeedback)
        val etFeedback = mDialogView.findViewById<EditText>(R.id.etAbout)
        val etAbout = mDialogView.findViewById<EditText>(R.id.reideas)

        val btnupdateData = mDialogView.findViewById<Button>(R.id.button)

        //setting data
        etServiceTitle.setText(intent.getStringExtra("serviceTitle").toString())
        etProviderName.setText(intent.getStringExtra("providerName").toString())
        etFeedback.setText(intent.getStringExtra("feedback").toString())
        etAbout.setText(intent.getStringExtra("about").toString())

        mDialog.setTitle("Updating $providerName Record ")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnupdateData.setOnClickListener{
            updateAdData(
                //get updated data
                feedId,
                etServiceTitle.text.toString(),
                etProviderName.text.toString(),
                etFeedback.text.toString(),
                etAbout.text.toString()

            )

            Toast.makeText(applicationContext, "Feedback Data Updated", Toast.LENGTH_LONG).show()

            //setting updated data to the text views
            tvServiceTitle.text =  etServiceTitle.text.toString()
            tvProviderName.text = etProviderName.text.toString()
            tvFeedback.text = etFeedback.text.toString()
            tvAbout.text = etAbout.text.toString()

            alertDialog.dismiss()

        }

    }

    private fun updateAdData(
        feedId: String,
        serviceTitle: String,
        providerName: String,
        feedback: String,
        about: String,

        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedback").child(feedId)
        val requestInfo = FeedbackModel(feedId,serviceTitle,providerName,feedback,about)

        dbRef.setValue(requestInfo)
    }


    private fun deleteRecord(feedId: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Feedback").child(feedId)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Feedback deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FeedbackDisplay::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{
                error ->
            Toast.makeText(this, "Feedback not deleted ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}