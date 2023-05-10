package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.adapters.payAdaptor
import com.example.mad_miniproject.models.paymentModel


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class frechPaymentActivity : AppCompatActivity() {

    private lateinit var payRecyclerView: RecyclerView
    private lateinit var payLoadingData: TextView
    private lateinit var payList: ArrayList<paymentModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frech_payment)

        var backarrow=findViewById<ImageButton>(R.id.bac)
        backarrow.setOnClickListener {
            val intent= Intent (this, dashboardActivity::class.java)
            startActivity(intent)

        }

        payRecyclerView = findViewById(R.id.rvPay)
        payRecyclerView.layoutManager = LinearLayoutManager(this)
        payRecyclerView.setHasFixedSize(true)
        payLoadingData = findViewById(R.id.payLoadingData)

        payList = arrayListOf<paymentModel>()


        getPaymentData()

    }

    private fun getPaymentData() {
        payRecyclerView.visibility = View.GONE
        payLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Payment")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                payList.clear()

                if (snapshot.exists()) {
                    for (paySnap in snapshot.children) {
                        val payData = paySnap.getValue(paymentModel::class.java)
                        payList.add(payData!!)
                    }
                    val mAdaptor = payAdaptor(payList)
                    payRecyclerView.adapter = mAdaptor

                    mAdaptor.setOnItemClickListener(object :payAdaptor.onItemClickListner{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@frechPaymentActivity, viewPaymentDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("payId", payList[position].payId)
                            intent.putExtra("crdNumber", payList[position].crdNumber)
                            intent.putExtra("month", payList[position].month)
                            intent.putExtra("year", payList[position].year)
                            intent.putExtra("cvn", payList[position].cvn)
                            startActivity(intent)
                        }

                    })
                    payRecyclerView.visibility = View.VISIBLE
                    payLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}


