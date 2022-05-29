package com.example.myapplication1

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.login.ProfesorProvider
import com.example.myapplication1.adapter.ProfesorAdapter
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_profesor_vista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfesorVista : AppCompatActivity() {
    var este = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profesor_vista)
        println("____-------------------------------------------------------------------------------------------------------------------PROFESORES@!#!@#!@#!@#!@#!@#!@#!@#")
        initRecyclerView()


    }
    fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerProfesorV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService2: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado2: Call<Profesores> = apiService2.existeProfesor(message.toInt())
        var id= 0
        resultado2.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                id = response.body()!!.idCarrera
                val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                val resultado: Call<List<Profesores>> = apiService.listprofesoresporinstituto(id)
                resultado.enqueue(object : Callback<List<Profesores>> {
                    override fun onFailure(call: Call<List<Profesores>>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<List<Profesores>>, response: Response<List<Profesores>>){
                        println(response.body()!!)
                        recyclerView.adapter = ProfesorAdapter(response.body()!!,this@ProfesorVista)
                    }
                })
            }

        })


    }


}
