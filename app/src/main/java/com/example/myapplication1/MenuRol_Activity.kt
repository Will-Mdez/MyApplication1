package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication1.Vicerectoria.MenuVicerectoria_activity
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.datosEditText
import kotlinx.android.synthetic.main.activity_menu_rol.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuRol_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_rol)
        val message: String = intent.getStringExtra("idUsuario").toString()

        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuRol_Activity,"Error", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {   var rol = response.body()!!.rol
                datosEditText.text=response.body()!!.grado+". "+response.body()!!.nombreProfesor+" "+response.body()!!.apellidoPaterno.toString()+" "+response.body()!!.apellidoMaterno

                if (response.body()!!.rol=="Vicerector"){
                    CardDirectorInstituto.setBackgroundColor(115799)
                    CardJefeCarrera.setBackgroundColor(115799)
                }else if (response.body()!!.rol=="Jefe Carrera"){
                    CardVicerector.setBackgroundColor(115799)
                    CardDirectorInstituto.setBackgroundColor(115799)
                }else if (response.body()!!.rol=="Director Instituto"){
                    CardVicerector.setBackgroundColor(115799)
                    CardJefeCarrera.setBackgroundColor(115799)
                }else{
                    CardVicerector.setBackgroundColor(115799)
                    CardJefeCarrera.setBackgroundColor(115799)
                    CardDirectorInstituto.setBackgroundColor(115799)
                }
            }
        })
    }

    fun eventoJC(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuRol_Activity,"Error", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                if (response.body()!!.rol=="Jefe Carrera"){
                    Toast.makeText(this@MenuRol_Activity,"NICEEEE", Toast.LENGTH_SHORT).show()
                    val menu = Intent(this@MenuRol_Activity, MenuJefeCarrera_activity::class.java).apply {
                        putExtra("idUsuario",""+message)
                    }
                    startActivity(menu)
                }else{
                    Toast.makeText(this@MenuRol_Activity,"Sin Permiso", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun eventoDI(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuRol_Activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                if (response.body()!!.rol=="Director Instituto"){
                    Toast.makeText(this@MenuRol_Activity,"NICEEEE", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@MenuRol_Activity,"Sin Permiso", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
    fun eventoProfesor(view: View){
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuRol_Activity,"Error", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                if (response.body()!!.rol=="Profesor"){
                    Toast.makeText(this@MenuRol_Activity,"NICEEEE", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this@MenuRol_Activity,"Sin Permiso", Toast.LENGTH_SHORT).show()
                }
            }
        })
        val menu = Intent(this, MenuProfesor_activity::class.java).apply {
            putExtra("idUsuario",""+message)
        }
        startActivity(menu)

    }

    fun eventovicerector(view: View) {

        val este = this
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@MenuRol_Activity,"Error", Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                if (response.body()!!.rol=="Vicerector"){
                    val message: String = intent.getStringExtra("idUsuario").toString()
                    val menu = Intent(this@MenuRol_Activity, MenuVicerectoria_activity::class.java).apply {
                        putExtra("idUsuario",""+message)
                    }
                    startActivity(menu)
                }else{
                    Toast.makeText(this@MenuRol_Activity,"Sin Permiso", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}