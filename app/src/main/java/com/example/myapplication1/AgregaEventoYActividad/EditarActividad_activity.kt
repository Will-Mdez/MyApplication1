package com.example.myapplication1.AgregaEventoYActividad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication1.*
import kotlinx.android.synthetic.main.activity_agregar_actividad.*
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import kotlinx.android.synthetic.main.activity_editar_actividad.*
import kotlinx.android.synthetic.main.activity_editar_actividad.FechaFinal
import kotlinx.android.synthetic.main.activity_editar_actividad.Fechainicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarActividad_activity : AppCompatActivity() {
    var idprofe=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_actividad)
        val message: String = intent.getStringExtra("idActividad").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<actividades> = apiService.unaActividad(message.toInt())
        resultado.enqueue(object : Callback<actividades>
        {
            override fun onFailure(call: Call<actividades>, t: Throwable)
            {
                //Toast.makeText(this@ActualizarPerfil_Activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<actividades>, response: Response<actividades>)
            {
                NombreEditText.setText(response.body()!!.actividad)
                DescripcionEditTextAE.setText(response.body()!!.descripcion)
                ComprobanteEditTextAE.setText(response.body()!!.comprobante)
                FechaFinal.setText(response.body()!!.fin)
                Fechainicio.setText(response.body()!!.inicio)
                idprofe=response.body()!!.idProfesor
            }

        })
    }

    fun SelectFechaInicioActividadA(view: View) {
        val fecha = AgregarActividad_activity.TomarFecha { year, mes, day ->mostrarResultado(year,mes,day )}
        fecha.show(supportFragmentManager,"date picker")
    }
    fun SelectFechaFinalActividadA(view: View) {
        val fecha = AgregarActividad_activity.TomarFecha { year, mes, day -> mostrarResultado2(year,mes,day) }
        fecha.show(supportFragmentManager,"date picker")
    }
    private fun mostrarResultado(year: Int, mes: Int, day: Any) {
        Fechainicio.text="$year-$mes-$day"

    }
    private fun mostrarResultado2(year: Int, mes: Int, day: Any) {
        FechaFinal.text="$year-$mes-$day"

    }
    fun actualizaractividad(view: View) {
        val message: String = intent.getStringExtra("idActividad").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val a= actividades(message.toInt(),idprofe,NombreEditText.text.toString(),Fechainicio.text.toString(),FechaFinal.text.toString(),DescripcionEditTextAE.text.toString(),1,ComprobanteEditTextAE.text.toString())
        println(a)
        val resultado: Call<actividades> = apiService.actualizarActividad(a,message.toInt())
        resultado.enqueue(object : Callback<actividades>
        {
            override fun onFailure(call: Call<actividades>, t: Throwable)
            {
                println("Fallo")
                Toast.makeText(this@EditarActividad_activity,"Error Registro", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<actividades>, response: Response<actividades>)
            {
                Toast.makeText(this@EditarActividad_activity,"Evento Registrado!!", Toast.LENGTH_LONG).show()
                println("Exito")
                println(response.isSuccessful)
                println(response.code())
            }
        })
    }
}