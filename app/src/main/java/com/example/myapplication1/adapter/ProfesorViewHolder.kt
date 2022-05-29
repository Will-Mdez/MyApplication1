package com.example.myapplication1.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.getIntent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_profesor_vista.*
import kotlinx.android.synthetic.main.activity_todos_profesores.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfesorViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val nombreProfesor=view.findViewById<TextView>(R.id.EditTextNombreProfesor)
    val carreraProfesor = view.findViewById<TextView>(R.id.EditTextcarreaProfesor)
    val institutoProfesor = view.findViewById<TextView>(R.id.EditTextinstitutoProfesor)
    val foto=view.findViewById<ImageView>(R.id.EditFoto)
    var botonEliminar=view.findViewById<ImageView>(R.id.botonEliminar)
    var botonEditar=view.findViewById<ImageView>(R.id.botonEditar)
    var idProfeHolder=0
    fun dibujar(profesorModel: Profesores, context:Context) {
        idProfeHolder=profesorModel.idProfesor
        nombreProfesor.text = profesorModel.nombreProfesor+" "+profesorModel.apellidoPaterno +" "+profesorModel.apellidoMaterno
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Institutos> = apiService.listOneInstituto(profesorModel.idInstituto)
        resultado.enqueue(object : Callback<Institutos>
        {
            override fun onFailure(call: Call<Institutos>, t: Throwable)
            {
                println("Error en Profesor View Holder")
            }
            override fun onResponse(call: Call<Institutos>, response: Response<Institutos>)
            {
                institutoProfesor.text=response.body()!!.nombreInstituto
            }

        })
        val apiService2: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado2: Call<Carrera> = apiService2.listOneCarreras(profesorModel.idCarrera)
        resultado2.enqueue(object : Callback<Carrera>
        {
            override fun onFailure(call: Call<Carrera>, t: Throwable)
            {
                println("Error en Profesor View Holder")
            }
            override fun onResponse(call: Call<Carrera>, response: Response<Carrera>)
            {
                carreraProfesor.text=response.body()!!.nombreCarrera
            }

        })

        val ruta_url="http://192.168.1.77:3000/img/id"+profesorModel.idProfesor.toString()+".png"
        Picasso.get().load(ruta_url).resize(150,150).into(foto)
        botonEliminar.setOnClickListener { val dialogo: AlertDialog =AlertDialog.Builder(context)
            .setPositiveButton(
                "Sí, eliminar",
                DialogInterface.OnClickListener { dialog, which ->
                    // Hicieron click en el botón positivo, así que la acción está confirmada
                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                    val id= idProfeHolder
                    val resultado: Call<Profesores> = apiService.eliminarProfesor(id)
                    resultado.enqueue(object : Callback<Profesores> {
                        override fun onFailure(call: Call<Profesores>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<Profesores>, response: Response<Profesores>){
                            println(response.body()!!)
                        }
                    })
                })
            .setNegativeButton(
                "Cancelar",
                DialogInterface.OnClickListener { dialog, which -> // Hicieron click en el botón negativo, no confirmaron
                    // Simplemente descartamos el diálogo
                    dialog.dismiss()
                })
            .setTitle("Confirmar") // El título
            .setMessage("¿Deseas eliminar Profesor?") // El mensaje
            .create()
            dialogo.show()}

        botonEditar.setOnClickListener {
            val message: String=profesorModel.idProfesor.toString()
            val general = Intent(context, ActualizarPerfil_Activity::class.java).apply {
                putExtra("idUsuario",message)
            }
            context.startActivity(general)
        }
    }


}