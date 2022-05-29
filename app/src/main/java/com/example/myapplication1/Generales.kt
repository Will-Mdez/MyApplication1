package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_generales.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Generales : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message: String = intent.getStringExtra("idUsuario").toString()
        setContentView(R.layout.activity_generales)

        val ruta_url="http://localhost:3000/img/id"+message+".png"
        Picasso.get().load(ruta_url).into(fotoEditImagen)


        val este=this;
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@Generales,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {

                nombreProfesorEditText.text=response.body()!!.nombreProfesor.toString()+" "+response.body()!!.apellidoPaterno.toString()+" "+response.body()!!.apellidoMaterno


                val IdCarrera = response.body()!!.idCarrera.toInt()
                GradoEditText.text=response.body()!!.grado.toString()
                val resultado: Call<Institutos> = apiService.listOneInstituto(response.body()!!.idInstituto.toInt())
                resultado.enqueue(object : Callback<Institutos>
                {
                    override fun onFailure(call: Call<Institutos>, t: Throwable)
                    {
                        Toast.makeText(this@Generales,"Error", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<Institutos>, response: Response<Institutos>)
                    {

                        nombreInstitutoEditText.text=response.body()!!.nombreInstituto.toString()

                    }
                })
                val resultado2 : Call<Carrera> = apiService.listOneCarreras(IdCarrera)
                resultado2.enqueue(object : Callback<Carrera>
                {
                    override fun onFailure(call: Call<Carrera>, t: Throwable)
                    {
                        Toast.makeText(this@Generales,"Error", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<Carrera>, response: Response<Carrera>)
                    {
                        Toast.makeText(this@Generales,"Ok", Toast.LENGTH_LONG).show()

                        CarreraEditText.text=response.body()!!.nombreCarrera.toString()

                    }
                })
            }

        })

    }

}


