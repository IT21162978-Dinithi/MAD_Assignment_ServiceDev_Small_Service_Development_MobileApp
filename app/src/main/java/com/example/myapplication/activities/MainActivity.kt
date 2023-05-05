package com.example.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btngo = findViewById<Button>(R.id.btngo)
        btngo.setOnClickListener {
            val intentP1 = Intent(this, addPaymentDetailsActivity::class.java)
            startActivity(intentP1)

        }
        var btnFetchPay = findViewById<Button>(R.id.btnFetchPay)
        btnFetchPay.setOnClickListener {
            val intentP6 = Intent(this, frechPaymentActivity::class.java)
            startActivity(intentP6)

        }
    }
}


