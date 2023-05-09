package com.example.mad_miniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.paymentModel


class payAdaptor(private val payList: ArrayList<paymentModel>):
    RecyclerView.Adapter<payAdaptor.ViewHolder>() {

    private lateinit var mListner: onItemClickListner
    interface onItemClickListner{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListner){
        mListner = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listpayment, parent, false)
        return ViewHolder(itemView, mListner)
    }
    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentPay = payList[position]
        holder.tvEmpName.text = currentPay.crdNumber
    }

    override fun getItemCount(): Int {
        return payList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemClickListner ) : RecyclerView.ViewHolder(itemView) {
           val tvEmpName : TextView =itemView.findViewById(R.id.tvEmpName)

        init {
          itemView.setOnClickListener {
              clickListener.onItemClick(adapterPosition)
          }
        }

     }
    }
