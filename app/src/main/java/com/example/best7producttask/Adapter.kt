package com.example.best7producttask

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_create_card.view.*
import kotlinx.android.synthetic.main.view.view.*

class Adapter(var data: List<CardInfo>) : RecyclerView.Adapter<Adapter.viewHolder>() {
    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.title
        var progvalue = itemView.progvalue
        val progressbar = itemView.progressBar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return viewHolder(itemView)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        holder.title.text = data[position].title
        holder.progvalue.text = data[position].intialvalue+"/"+data[position].finalvalue
        holder.progressbar.progress = data[position].intialvalue.toInt()
        holder.progressbar.max = data[position].finalvalue.toInt()
        holder.itemView.setOnClickListener{
            val intent= Intent(holder.itemView.context,UpdateCard::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)

        }


    }

    override fun getItemCount(): Int {
        return data.size
    }
}