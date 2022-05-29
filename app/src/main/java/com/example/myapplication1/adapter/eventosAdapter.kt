package com.example.myapplication1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.Profesores
import com.example.myapplication1.R
import com.example.myapplication1.eventos

class eventosAdapter (private val eventosList:List<eventos>, context: Context) : RecyclerView.Adapter<eventosViewHolder>(){
    var este = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eventosViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return eventosViewHolder(layoutInflater.inflate(R.layout.item_eventos,parent,false))

    }

    override fun onBindViewHolder(holder: eventosViewHolder, position: Int) {
        val item=eventosList[position]
        holder.dibujarevento(item, este)
    }

    override fun getItemCount(): Int {
        return eventosList.count()
    }
}