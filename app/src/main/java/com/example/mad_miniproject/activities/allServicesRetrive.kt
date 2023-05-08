package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.adapters.SevRetriveAdapter
import com.example.mad_miniproject.models.AllServicesModel
import com.google.firebase.database.*

class allServicesRetrive : AppCompatActivity() {

    private lateinit var allServicesRecyclerView: RecyclerView
    private lateinit var servicesList : ArrayList<AllServicesModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_services_retrive)

        allServicesRecyclerView = findViewById(R.id.rvBooking)
        allServicesRecyclerView.layoutManager= LinearLayoutManager(this)
        allServicesRecyclerView.setHasFixedSize(true)


        servicesList = arrayListOf<AllServicesModel>()

        getsevData()


    }

    private fun getsevData(){

        dbRef = FirebaseDatabase.getInstance().getReference("Services")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for( sevaSnapshot in snapshot.children){
                        val seva = sevaSnapshot.getValue(AllServicesModel::class.java)


                        servicesList.add(seva!!)
                    }

                    val mAdapter = SevRetriveAdapter(servicesList)
                    allServicesRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : SevRetriveAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent =Intent(this@allServicesRetrive,retriveBookingdata::class.java)

                            //put Extra
                            intent.putExtra("addservices",servicesList[position].addservices)
                            intent.putExtra("adddescription",servicesList[position].adddescription)

                            startActivity(intent)
                        }


                    })






                    //allServicesRecyclerView.adapter = SevRetriveAdapter(servicesList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}