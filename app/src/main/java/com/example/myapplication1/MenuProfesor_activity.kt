package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuProfesor_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_profesor)
        val message: String = intent.getStringExtra("idUsuario").toString()

        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuProfesor_activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                Toast.makeText(this@MenuProfesor_activity,"Ok", Toast.LENGTH_LONG).show()
                print(response.body().toString())
                datosEditText.text=response.body()!!.grado.toString()+". "+response.body()!!.nombreProfesor.toString()+" "+response.body()!!.apellidoPaterno.toString()+" "+response.body()!!.apellidoMaterno
            }

        })
    }



    fun datosgeneralesprofesor(view: View) {
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val general = Intent(este, Generales::class.java).apply {
            //PutExtra manda parámetros
            //El name es la llave del diccionario
            putExtra("idUsuario",message)
        }
        startActivity(general)
    }

    fun listareventosact(view: View) {
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val general = Intent(este, eventosVista_activity::class.java).apply {
            //PutExtra manda parámetros
            //El name es la llave del diccionario
            putExtra("idUsuario",message)
        }
        startActivity(general)
    }

    fun listaractividadesact(view: View) {
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val general = Intent(este, actividadesVista_activity::class.java).apply {
            //PutExtra manda parámetros
            //El name es la llave del diccionario
            putExtra("idUsuario",message)
        }
        startActivity(general)
    }
    fun listararticulosact(view: View) {
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val general = Intent(este, ProfeYArt::class.java).apply {
            //PutExtra manda parámetros
            //El name es la llave del diccionario
            putExtra("idUsuario",message)
        }
        startActivity(general)
    }
}