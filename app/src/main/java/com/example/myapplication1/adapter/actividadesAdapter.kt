package com.example.myapplication1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.R
import com.example.myapplication1.actividades
import com.example.myapplication1.eventos

class actividadesAdapter(private val actividadesList:List<actividades>, context: Context) : RecyclerView.Adapter<actividadesViewHolder>() {
    var este = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): actividadesViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return actividadesViewHolder(layoutInflater.inflate(R.layout.item_actividades,parent,false))

    }

    override fun onBindViewHolder(holder: actividadesViewHolder, position: Int) {
        val item=actividadesList[position]
        holder.dibujaractividades(item, este)
    }

    override fun getItemCount(): Int {
        return actividadesList.count()
    }
}