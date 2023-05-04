package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class calcServiceChargeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_service_charge)

        var btn_delete = findViewById<Button>(R.id.btn_delete)
        btn_delete.setOnClickListener {
            val intentP3 = Intent(this, addPaymentDetailsActivity::class.java)
            startActivity(intentP3)

        }
    }
}