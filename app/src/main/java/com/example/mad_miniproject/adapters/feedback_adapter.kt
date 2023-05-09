package com.example.mad_miniproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_miniproject.R
import com.example.mad_miniproject.models.FeedbackModel



class feedback_adapter (private val adList: ArrayList<FeedbackModel>) :
    RecyclerView.Adapter<feedback_adapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_feedback,parent,false)
        return ViewHolder(adView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAd = adList[position]
        holder.reSname.text = currentAd.providerName
    }

    override fun getItemCount(): Int {
        return adList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val reSname : TextView = itemView.findViewById(R.id.reSname)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}
