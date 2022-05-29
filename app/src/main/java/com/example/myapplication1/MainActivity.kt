package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(100)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun registrase(view: View){
        val este=this;
        val registrar = Intent(este, RegistroProfesor_Activity::class.java).apply {
            //PutExtra manda parámetros
            //El name es la llave del diccionario
        }
        //startActivity(general)
        startActivity(registrar)
    }
    fun login(view: View)
    {
        val este=this;
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<usuario> = apiService.existe(usuarioEditText.text.toString(),passwordEditText.text.toString())
        resultado.enqueue(object : Callback<usuario>
        {
            override fun onFailure(call: Call<usuario>, t: Throwable)
            {
                Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<usuario>,response: Response<usuario>)
            {
                Toast.makeText(this@MainActivity,"Ok",Toast.LENGTH_SHORT).show()
                println(response.body()!!.idUsuarios)

                val general = Intent(este, Generales::class.java).apply {
                    putExtra("idUsuario",""+response.body()!!.idUsuarios)
                }
                val menu = Intent(este, Menu::class.java).apply {
                    putExtra("idUsuario",""+response.body()!!.idUsuarios)
                }
                val menurol = Intent(este, MenuRol_Activity::class.java).apply {
                    putExtra("idUsuario",""+response.body()!!.idUsuarios)
                }
                //startActivity(general)
                startActivity(menurol)
            }
        })
    }

}