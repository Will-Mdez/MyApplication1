package com.example.myapplication1.Vicerectoria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication1.JefeCarrera.MenuAgregarJC_activity
import com.example.myapplication1.JefeCarrera.MenuListarJC_activity
import com.example.myapplication1.R
import com.example.myapplication1.RegistroProfesor_Activity

class MenuVicerectoria_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_vicerectoria)
    }

    fun listar(view: View){
    }
    fun agregar(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, RegistroProfesor_Activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
}