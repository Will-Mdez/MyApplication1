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
import com.example.myapplication1.AgregaEventoYActividad.EditarEvento_activity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class eventosViewHolder (view : View): RecyclerView.ViewHolder(view){
    val nombreEvento =view.findViewById<TextView>(R.id.EditTextEvento)
    val participacion = view.findViewById<TextView>(R.id.EditTextParticipacion)
    val tipo = view.findViewById<TextView>(R.id.EditTextTipo)
    val tipoparticipacion =view.findViewById<TextView>(R.id.EditTextTipoParticipacion)
    val fechaInicioevento = view.findViewById<TextView>(R.id.EditTextFechaInicioEvento)
    val fechaFinEvento = view.findViewById<TextView>(R.id.EditTextFechaFinEvento)
    var botonEliminar=view.findViewById<ImageView>(R.id.botonEliminarE)
    var botonEditar=view.findViewById<ImageView>(R.id.botonEditarE)
    var idEventoHolder=0
    fun dibujarevento(eventoModel: eventos, context: Context) {
        nombreEvento.text = eventoModel.nombreEvento
        participacion.text = eventoModel.participacion
        tipo.text=eventoModel.tipoEvento
        tipoparticipacion.text= eventoModel.tipoParticipacion
        fechaInicioevento.text=eventoModel.inicio
        fechaFinEvento.text=eventoModel.fin
        idEventoHolder=eventoModel.id
        botonEliminar.setOnClickListener { val dialogo: AlertDialog = AlertDialog.Builder(context)
            .setPositiveButton(
                "Sí, eliminar",
                DialogInterface.OnClickListener { dialog, which ->
                    // Hicieron click en el botón positivo, así que la acción está confirmada
                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                    val id= idEventoHolder
                    val resultado: Call<eventos> = apiService.eliminarEvento(id)
                    resultado.enqueue(object : Callback<eventos> {
                        override fun onFailure(call: Call<eventos>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<eventos>, response: Response<eventos>){
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
            .setMessage("¿Deseas eliminar Evento?") // El mensaje
            .create()
            dialogo.show()}
        botonEditar.setOnClickListener {
            val message: String=eventoModel.id.toString()
            val general = Intent(context, EditarEvento_activity::class.java).apply {
                putExtra("idEvento",message)
            }
            context.startActivity(general)
        }
    }


}