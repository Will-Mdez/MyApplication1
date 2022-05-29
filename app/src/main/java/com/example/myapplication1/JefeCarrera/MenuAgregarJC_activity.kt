package com.example.myapplication1.JefeCarrera

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication1.AgregaEventoYActividad.AgregarActividad_activity
import com.example.myapplication1.AgregaEventoYActividad.AgregarEvento_activity
import com.example.myapplication1.R
import com.example.myapplication1.RegistroProfesor_Activity
import com.example.myapplication1.Vicerectoria.MenuVicerectoria_activity

class MenuAgregarJC_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_agregar_jc)

    }

    fun agregarprofe(view: View) {
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, RegistroProfesor_Activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
    fun agregarevento(view: View) {
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, AgregarEvento_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
    fun agregaractividad(view: View) {
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, AgregarActividad_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
}