package com.example.myapplication1.JefeCarrera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication1.ProfesorVista
import com.example.myapplication1.R
import com.example.myapplication1.actividadesVista_activity
import com.example.myapplication1.eventosVista_activity

class MenuListarJC_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_listar_jc)
    }
    fun listarProfesores(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, ProfesorVista::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
    fun listarEventos(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, eventosVista_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
    fun listarActividades(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, actividadesVista_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
}