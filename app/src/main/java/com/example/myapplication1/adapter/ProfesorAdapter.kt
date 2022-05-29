package com.example.myapplication1.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.Profesores
import com.example.myapplication1.R


class ProfesorAdapter(private val ProfesorList:List<Profesores>, context:Context) : RecyclerView.Adapter<ProfesorViewHolder>(){
    var este = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesorViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ProfesorViewHolder(layoutInflater.inflate(R.layout.item_profesor,parent,false))

    }

    override fun onBindViewHolder(holder: ProfesorViewHolder, position: Int) {
        val item=ProfesorList[position]
        holder.dibujar(item, este)
    }

    override fun getItemCount(): Int {
        return ProfesorList.count()
    }
}