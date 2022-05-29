package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_generales.*
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val message: String = intent.getStringExtra("idUsuario").toString()
        val este=this;
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@Menu,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                Toast.makeText(this@Menu,"Ok", Toast.LENGTH_LONG).show()
                print(response.body().toString())
                datosEditText.text=response.body()!!.grado.toString()+". "+response.body()!!.nombreProfesor.toString()+" "+response.body()!!.apellidoPaterno.toString()+" "+response.body()!!.apellidoMaterno
            }

        })
    }
    fun datosgenerales(view: View) {
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val general = Intent(este, Generales::class.java).apply {
            //PutExtra manda parámetros
            //El name es la llave del diccionario
            putExtra("idUsuario",message)
        }
        startActivity(general)
    }

    fun ProfesoresTodos(view: View) {
        val este=this;
        //val general = Intent(este, TodosProfesores::class.java).apply {
        val general = Intent(este, ProfesorVista::class.java).apply {
        }
        startActivity(general)
    }
    fun EditarProf(view: View) {
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        //val general = Intent(este, TodosProfesores::class.java).apply {
        val general = Intent(este, ActualizarPerfil_Activity::class.java).apply {
            putExtra("idUsuario",message)
        }
        startActivity(general)
    }



    fun listarArticulos(view: View){
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