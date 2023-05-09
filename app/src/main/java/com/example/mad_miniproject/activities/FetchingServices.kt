package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.adapters.ServiceAdapter
import com.example.mad_miniproject.models.ServicesModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingServices : AppCompatActivity() {

    private lateinit var sevRecyclerView: RecyclerView
    private lateinit var sevLoadingData: TextView
    private lateinit var sevArrayList: ArrayList<ServicesModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching_services)

        var addsernw=findViewById<Button>(R.id.addsernw)
        addsernw.setOnClickListener {
            val intent= Intent (this, AddServices::class.java)
            startActivity(intent)

        }

        var backarrow=findViewById<ImageButton>(R.id.backarrow4)
        backarrow.setOnClickListener {
            val intent= Intent (this, dashboardActivity::class.java)
            startActivity(intent)

        }

        sevRecyclerView = findViewById(R.id.tvsev)
        sevRecyclerView.layoutManager = LinearLayoutManager(this)
        sevRecyclerView.setHasFixedSize(true)
        sevLoadingData = findViewById(R.id.sevLoadingData)

        sevArrayList = arrayListOf<ServicesModel>()

        getSevData()

    }

    private fun getSevData() {

        sevRecyclerView.visibility = View.GONE
        sevLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Services")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                sevArrayList.clear()
                if (snapshot.exists()){
                    for (sevSnap in snapshot.children){
                        val sevData = sevSnap.getValue(ServicesModel::class.java)
                        sevArrayList.add(sevData!!)
                    }
                    val mAdapter = ServiceAdapter(sevArrayList)
                    sevRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : ServiceAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingServices, ServiceDetails::class.java)

                            //put extras
                            intent.putExtra("serviceID", sevArrayList[position].serviceID)
                            intent.putExtra("addservices", sevArrayList[position].addservices)
                            intent.putExtra("addnic", sevArrayList[position].addnic)
                            intent.putExtra("addage", sevArrayList[position].addage)
                            intent.putExtra("addgender", sevArrayList[position].addgender)
                            intent.putExtra("addaddress", sevArrayList[position].addaddress)
                            intent.putExtra("addhour", sevArrayList[position].addhour)
                            intent.putExtra("addavailable", sevArrayList[position].addavailable)
                            intent.putExtra("adddescription", sevArrayList[position].adddescription)


                            startActivity(intent)
                        }

                    })

                    sevRecyclerView.visibility = View.VISIBLE
                    sevLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
