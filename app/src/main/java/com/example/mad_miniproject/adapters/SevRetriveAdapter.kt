package com.example.mad_miniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.AllServicesModel

class SevRetriveAdapter(private val sevList: ArrayList<AllServicesModel>) : RecyclerView.Adapter<SevRetriveAdapter.MyViewHolder>() {


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.allsevlist,
        parent,false)

        return MyViewHolder(itemView,mListener)
    }

    override fun getItemCount(): Int {
        return sevList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentService = sevList[position]
        holder.Name.text = currentService.adddescription
        holder.Title.text = currentService.addservices
        holder.Age.text = currentService.addage
        holder.gender.text = currentService.addgender
        holder.hr.text = currentService.addhour
        holder.Ava.text = currentService.addavailable
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    class MyViewHolder(itemView: View, clickListener:onItemClickListener): RecyclerView.ViewHolder(itemView){

        val Name : TextView = itemView.findViewById(R.id.seName)
        val Title : TextView = itemView.findViewById(R.id.seTitle)
        val Age : TextView = itemView.findViewById(R.id.seAge)
        val gender : TextView = itemView.findViewById(R.id.segender)
        val hr : TextView = itemView.findViewById(R.id.sehr)
        val Ava : TextView = itemView.findViewById(R.id.seAva)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }


    }

}