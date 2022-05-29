package com.example.myapplication1.AgregaEventoYActividad

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.myapplication1.*
import kotlinx.android.synthetic.main.activity_agregar_actividad.*
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import kotlinx.android.synthetic.main.activity_agregar_evento.FechaFinal
import kotlinx.android.synthetic.main.activity_agregar_evento.Fechainicio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AgregarActividad_activity : AppCompatActivity() {
    var id=3
    var listaprofesores=ArrayList<String>()
    var idSeleccionado =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_actividad)

        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService11: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado11: Call<Profesores> = apiService11.existeProfesor(message.toInt())
        resultado11.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@AgregarActividad_activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                //Seleccionar Profsor

                val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                val resultado: Call<List<Profesores>> = apiService.listprofesoresporinstituto(response.body()!!.idInstituto)
                resultado.enqueue(object : Callback<List<Profesores>> {
                    override fun onFailure(call: Call<List<Profesores>>, t: Throwable) {
                    }
                    override fun onResponse(call: Call<List<Profesores>>, response: Response<List<Profesores>>){


                        for (i in 0 .. response.body()!!.size-1){
                            listaprofesores.add(response.body()!![i].nombreProfesor+" "+response.body()!![i].apellidoPaterno)

                        }
                        println(listaprofesores)

                        val spinner= findViewById<Spinner>(R.id.SeleccionarProfesor)
                        var arrayAdapter= ArrayAdapter<String>(this@AgregarActividad_activity,android.R.layout.simple_spinner_item,listaprofesores)
                        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinner.adapter = arrayAdapter
                        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                                idSeleccionado=response.body()!![pos].idProfesor
                            }
                            override fun onNothingSelected(p0: AdapterView<*>?) {
                                TODO("Not yet implemented")
                            }
                        }

                    }
                })
            }

        })

    }

    fun altaactividad(view: View) {
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val a= actividades(0,idSeleccionado,NombreAEditText.text.toString(),Fechainicio.text.toString(),FechaFinal.text.toString(),DescripcionEditText.text.toString(),1,ComprovanteAEditText.text.toString())
        println(a)
        val resultado: Call<actividades> = apiService.insertarActividad(a)
        resultado.enqueue(object : Callback<actividades>
        {
            override fun onFailure(call: Call<actividades>, t: Throwable)
            {
                println("Fallo")
                Toast.makeText(this@AgregarActividad_activity,"Error Registro", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<actividades>, response: Response<actividades>)
            {
                Toast.makeText(this@AgregarActividad_activity,"Evento Registrado!!", Toast.LENGTH_LONG).show()
                println("Exito")
                println(response.isSuccessful)
                println(response.code())
            }
        })
    }

    fun SelectFechaInicioActividad(view: View) {
        val fecha =TomarFecha { year, mes, day -> mostrarResultado(year, mes, day) }
        fecha.show(supportFragmentManager,"date picker")
    }
    fun SelectFechaFinalActividad(view: View) {
        val fecha = TomarFecha { year, mes, day ->mostrarResultado2(year,mes,day)}
        fecha.show(supportFragmentManager,"date picker")
    }
    private fun mostrarResultado(year: Int, mes: Int, day: Any) {
        Fechainicio.text="$year-$mes-$day"

    }
    private fun mostrarResultado2(year: Int, mes: Int, day: Any) {
        FechaFinal.text="$year-$mes-$day"

    }
    class TomarFecha(val listener:(year:Int, mes:Int,day:Int)->Unit): DialogFragment(),
        DatePickerDialog.OnDateSetListener{

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val mes = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            return DatePickerDialog(requireActivity(),this,year,mes,day)
        }
        override fun onDateSet(p0: DatePicker?, year: Int, mes: Int, day: Int) {
            listener(year,mes,day)
        }
    }
}