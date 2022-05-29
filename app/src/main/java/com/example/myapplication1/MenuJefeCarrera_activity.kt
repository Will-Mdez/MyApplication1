package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication1.JefeCarrera.MenuAgregarJC_activity
import com.example.myapplication1.JefeCarrera.MenuListarJC_activity
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuJefeCarrera_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_jefe_carrera)
        val message: String = intent.getStringExtra("idUsuario").toString()

        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuJefeCarrera_activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                Toast.makeText(this@MenuJefeCarrera_activity,"Ok", Toast.LENGTH_LONG).show()
                print(response.body().toString())
                datosEditText.text=response.body()!!.grado.toString()+". "+response.body()!!.nombreProfesor.toString()+" "+response.body()!!.apellidoPaterno.toString()+" "+response.body()!!.apellidoMaterno
            }
        })
    }
    fun listar(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, MenuListarJC_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)

    }
    fun agregar(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val menu = Intent(this, MenuAgregarJC_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)
    }
    fun asignar(view: View){

    }
}