package com.example.mad_miniproject.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.adapters.SevRetriveAdapter
import com.example.mad_miniproject.models.AllServicesModel
import com.google.firebase.database.*
import java.util.*

class allServicesRetrive : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var adapter: SevRetriveAdapter


    private lateinit var allServicesRecyclerView: RecyclerView
    private lateinit var servicesList : ArrayList<AllServicesModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_services_retrive)

        var backarrow=findViewById<ImageButton>(R.id.backarrow3)
        backarrow.setOnClickListener {
            val intent= Intent (this, dashboardActivity::class.java)
            startActivity(intent)

        }



        allServicesRecyclerView = findViewById(R.id.rvfeed)
        allServicesRecyclerView.layoutManager= LinearLayoutManager(this)
        allServicesRecyclerView.setHasFixedSize(true)



        servicesList = arrayListOf<AllServicesModel>()

        getsevData()

        //search view

        adapter = SevRetriveAdapter(servicesList)
        //
        allServicesRecyclerView.adapter = adapter

        searchView = findViewById(R.id.search)

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText : String): Boolean {
                filterList(newText)
                return true
            }


        })

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

    //search function
    private fun filterList(query: String?){

        if (query!=null) {
            val filteredList = ArrayList<AllServicesModel>()
            for (seva in servicesList) {
                if (seva.addservices?.lowercase(Locale.ROOT)?.contains(query) == true) {
                    filteredList.add(seva)

                }
            }



            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
            } else {

                //allServicesRecyclerView.adapter =  SevRetriveAdapter(filteredList)
                adapter.setFilteredList(filteredList)
            }
        }

    }

//    fun filterList(text: String) {
//        val filteredList = java.util.ArrayList<AllServicesModel>()
//        for (seva in servicesList) {
//            if (seva.addservices?.lowercase()
//                    ?.contains(text.lowercase(Locale.getDefault())) == true
//            ) {
//                filteredList.add(seva)
//            }
//        }
//        adapter.setFilteredList(filteredList)
//    }
}