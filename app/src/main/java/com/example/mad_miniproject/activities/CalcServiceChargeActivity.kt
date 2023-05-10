package com.example.mad_miniproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mad_miniproject.R


class calcServiceChargeActivity : AppCompatActivity() {private  var a:Double =15.0
    private  var b:Double =0.0
    private  var ans:Double =0.0

    private lateinit var num1 : EditText
    private lateinit var text : TextView
    private lateinit var btn :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_service_charge)

        num1=findViewById(R.id.hourtext)

        text = findViewById(R.id.paytext)
        btn = findViewById(R.id.calc)

        btn.setOnClickListener{
            a =Integer.parseInt(num1.text.toString()).toDouble()
            //b =Integer.parseInt(num2.text.toString()).toDouble()

            ans= a * 250.0
            text.setText(ans.toString())
        }




        var btn_delete = findViewById<Button>(R.id.btn_delete)
        btn_delete.setOnClickListener {
            val intentP3 = Intent(this, addPaymentDetailsActivity::class.java)
            startActivity(intentP3)

        }

        var btn_back=findViewById<Button>(R.id.btn_back)
        btn_back.setOnClickListener {
            val intent= Intent (this, dashboardActivity::class.java)
            startActivity(intent)

        }
    }
}