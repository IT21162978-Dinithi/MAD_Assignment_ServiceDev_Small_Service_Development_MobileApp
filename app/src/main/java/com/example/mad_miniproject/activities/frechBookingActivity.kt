package com.example.mad_miniproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.adapters.BookingListAdapter
import com.example.mad_miniproject.models.BookingModel
import com.google.firebase.database.*

class frechBookingActivity : AppCompatActivity() {

    private lateinit var bookingRecyclerView: RecyclerView
    private lateinit var bookingList : ArrayList<BookingModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frech_booking)

        bookingRecyclerView = findViewById(R.id.rvBooking)
        bookingRecyclerView.layoutManager=LinearLayoutManager(this)
        bookingRecyclerView.setHasFixedSize(true)

        bookingList = arrayListOf<BookingModel>()

        getBookingData()

    }

    private fun getBookingData(){
        bookingRecyclerView.visibility = View.GONE

        //database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Booking")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                bookingList.clear()
                if (snapshot.exists()){
                    for (bookingSnap in snapshot.children){
                        val bookingData = bookingSnap.getValue(BookingModel::class.java)

                        bookingList.add(bookingData!!)
                    }
                    val mAdapter = BookingListAdapter(bookingList)
                    bookingRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : BookingListAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@frechBookingActivity, BookingDetailedActivity::class.java)

                            //put extras
                            intent.putExtra("bookingId",bookingList[position].bookingId)
                            intent.putExtra("bookingHours",bookingList[position].bookingHours)
                            intent.putExtra("bookingDate",bookingList[position].bookingDate)
                            intent.putExtra("bookingAddr",bookingList[position].bookingAddr)
                            intent.putExtra("bookingPhone",bookingList[position].bookingPhone)
                            intent.putExtra("bookingSName",bookingList[position].bookingSName)

                            startActivity(intent)

                        }

                    })

                    bookingRecyclerView.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}