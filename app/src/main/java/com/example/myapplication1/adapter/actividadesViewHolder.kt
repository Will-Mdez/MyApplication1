package com.example.myapplication1.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.*
import com.example.myapplication1.AgregaEventoYActividad.EditarActividad_activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class actividadesViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val nombreActividad =view.findViewById<TextView>(R.id.EditTextActividad)
    val descripcion = view.findViewById<TextView>(R.id.EditTextDescripcion)
    val comprobante = view.findViewById<TextView>(R.id.EditTextComprobante)
    val fechainicio =view.findViewById<TextView>(R.id.EditTextFechaInicioActividad)
    val fechafin = view.findViewById<TextView>(R.id.EditTextFechaFinActividad)
    var botonEliminar=view.findViewById<ImageView>(R.id.botonEliminarA)
    var botonEditar=view.findViewById<ImageView>(R.id.botonEditarA)
    var idActividadHolder=0
    fun dibujaractividades(actividadesModel: actividades, context: Context){
        nombreActividad.text=actividadesModel.actividad
        descripcion.text=actividadesModel.descripcion
        comprobante.text=actividadesModel.comprobante
        fechainicio.text=actividadesModel.inicio
        fechafin.text=actividadesModel.fin
        idActividadHolder=actividadesModel.idActividad
        botonEliminar.setOnClickListener { val dialogo: AlertDialog = AlertDialog.Builder(context)
            .setPositiveButton(
                "Sí, eliminar",
                DialogInterface.OnClickListener { dialog, which ->
                    // Hicieron click en el botón positivo, así que la acción está confirmada
                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                    val id= idActividadHolder
                    val resultado: Call<actividades> = apiService.eliminarActividad(id)
                    resultado.enqueue(object : Callback<actividades> {
                        override fun onFailure(call: Call<actividades>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<actividades>, response: Response<actividades>){
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
            .setMessage("¿Deseas eliminar Actividad?") // El mensaje
            .create()
            dialogo.show()}
        botonEditar.setOnClickListener {
            val message: String=actividadesModel.idActividad.toString()
            val general = Intent(context, EditarActividad_activity::class.java).apply {
                putExtra("idActividad",message)
            }
            context.startActivity(general)
        }
    }
}