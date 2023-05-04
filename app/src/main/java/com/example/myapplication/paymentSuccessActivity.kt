package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class paymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_success)

        var btn_save = findViewById<Button>(R.id.btn_save)
        btn_save.setOnClickListener {
            val intentP5 = Intent(this, viewPaymentDetailsActivity::class.java)
            startActivity(intentP5)
        }
    }
}