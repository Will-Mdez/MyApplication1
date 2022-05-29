package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_generales.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_todos_profesores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodosProfesores : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos_profesores)
        val message: String = intent.getStringExtra("idUsuario").toString()


        val este=this;
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<List<Profesores>> = apiService.listprofesores()
        resultado.enqueue(object : Callback<List<Profesores>>
        {
            override fun onFailure(call: Call<List<Profesores>>, t: Throwable)
            {
                Toast.makeText(this@TodosProfesores,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<Profesores>>, response: Response<List<Profesores>>)
            {
                Toast.makeText(this@TodosProfesores,"Ok", Toast.LENGTH_LONG).show()
                val ruta_url="http://192.168.1.77:3000/img/id"+response.body()!![0].idProfesor.toString()+".png"
                Picasso.get().load(ruta_url).into(ImgProfe1)
                Nombre1.text=response.body()!![0].grado+response.body()!![0].nombreProfesor.toString()+" "+response.body()!![0].apellidoPaterno.toString()+" "+response.body()!![0].apellidoMaterno

                if(response.body()!![0].idInstituto==1){
                    Instituto1.text="Instituto Ingenieria Computacion"
                }else if(response.body()!![0].idInstituto==2){
                    Instituto1.text="Intituto de Electronica y Mecatronica"
                }else if(response.body()!![0].idInstituto==3){
                    Instituto1.text="Instituto de Fisica y Matematicas"
                }

                if(response.body()!![0].idCarrera==1){
                    Carrera1.text="Ingenieria Computacion"
                }else if(response.body()!![0].idCarrera==2){
                    Carrera1.text="Ingenieria de Electronica"
                }else if(response.body()!![0].idCarrera==3){
                    Carrera1.text="Licenciatura en Matematicas Aplicadas"
                }

                val ruta2="http://192.168.1.77:3000/img/id"+response.body()!![1].idProfesor.toString()+".png"
                Picasso.get().load(ruta2).into(ImgProfe2)
                Nombre2.text=response.body()!![1].grado+response.body()!![1].nombreProfesor.toString()+" "+response.body()!![1].apellidoPaterno.toString()+" "+response.body()!![1].apellidoMaterno


                if(response.body()!![1].idInstituto==1){
                    Instituto2.text="Instituto Ingenieria Computacion"
                }else if(response.body()!![1].idInstituto==2){
                    Instituto2.text="Intituto de Electronica y Mecatronica"
                }else if(response.body()!![1].idInstituto==3){
                    Instituto2.text="Instituto de Fisica y Matematicas"
                }

                if(response.body()!![1].idCarrera==1){
                    Carrera2.text="Ingenieria Computacion"
                }else if(response.body()!![1].idCarrera==2){
                    Carrera2.text="Ingenieria de Electronica"
                }else if(response.body()!![1].idCarrera==3){
                    Carrera2.text="Licenciatura en Matematicas Aplicadas"
                }


                var ruta3="http://192.168.1.77:3000/img/id"+response.body()!![2].idProfesor.toString()+".png"
                Picasso.get().load(ruta3).into(ImgProfe3)
                Nombre3.text=response.body()!![2].grado+response.body()!![2].nombreProfesor.toString()+" "+response.body()!![2].apellidoPaterno.toString()+" "+response.body()!![2].apellidoMaterno


                if(response.body()!![2].idInstituto==1){
                    Instituto3.text="Instituto Ingenieria Computacion"
                }else if(response.body()!![2].idInstituto==2){
                    Instituto3.text="Intituto de Electronica y Mecatronica"
                }else if(response.body()!![2].idInstituto==3){
                    Instituto3.text="Instituto de Fisica y Matematicas"
                }

                if(response.body()!![2].idCarrera==1){
                    Carrera3.text="Ingenieria Computacion"
                }else if(response.body()!![2].idCarrera==2){
                    Carrera3.text="Ingenieria de Electronica"
                }else if(response.body()!![2].idCarrera==3){
                    Carrera3.text="Licenciatura en Matematicas Aplicadas"
                }

                ruta3="http://192.168.1.77:3000/img/id"+response.body()!![3].idProfesor.toString()+".png"
                Picasso.get().load(ruta3).into(ImgProfe4)
                Nombre4.text=response.body()!![3].grado+response.body()!![3].nombreProfesor.toString()+" "+response.body()!![3].apellidoPaterno.toString()+" "+response.body()!![3].apellidoMaterno


                if(response.body()!![3].idInstituto==1){
                    Instituto4.text="Instituto Ingenieria Computacion"
                }else if(response.body()!![3].idInstituto==2){
                    Instituto4.text="Intituto de Electronica y Mecatronica"
                }else if(response.body()!![3].idInstituto==3){
                    Instituto4.text="Instituto de Fisica y Matematicas"
                }

                if(response.body()!![3].idCarrera==1){
                    Carrera4.text="Ingenieria Computacion"
                }else if(response.body()!![3].idCarrera==2){
                    Carrera4.text="Ingenieria de Electronica"
                }else if(response.body()!![3].idCarrera==3){
                    Carrera4.text="Licenciatura en Matematicas Aplicadas"
                }

                //5

                ruta3="http://192.168.1.77:3000/img/id"+response.body()!![4].idProfesor.toString()+".png"
                Picasso.get().load(ruta3).into(ImgProfe5)
                Nombre5.text=response.body()!![4].grado+response.body()!![4].nombreProfesor.toString()+" "+response.body()!![4].apellidoPaterno.toString()+" "+response.body()!![4].apellidoMaterno
                response.body()!![4].grado

                if(response.body()!![4].idInstituto==1){
                    Instituto5.text="Instituto Ingenieria Computacion"
                }else if(response.body()!![4].idInstituto==2){
                    Instituto5.text="Intituto de Electronica y Mecatronica"
                }else if(response.body()!![4].idInstituto==3){
                    Instituto5.text="Instituto de Fisica y Matematicas"
                }

                if(response.body()!![4].idCarrera==1){
                    Carrera5.text="Ingenieria Computacion"
                }else if(response.body()!![4].idCarrera==2){
                    Carrera5.text="Ingenieria de Electronica"
                }else if(response.body()!![4].idCarrera==3){
                    Carrera5.text="Licenciatura en Matematicas Aplicadas"
                }






            }

        })
    }

}