package com.example.myapplication1.AgregaEventoYActividad

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isInvisible
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.*
import com.example.myapplication1.adapter.eventosAdapter
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import kotlinx.android.synthetic.main.activity_agregar_evento.FechaFinal
import kotlinx.android.synthetic.main.activity_agregar_evento.Fechainicio
import kotlinx.android.synthetic.main.activity_eventos_vista.*
import kotlinx.android.synthetic.main.activity_profe_yart.*
import kotlinx.android.synthetic.main.activity_registro_profesor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AgregarEvento_activity : AppCompatActivity() {
    val tipoEvento=arrayOf("Congreso","Seminario","Curso","Taller","Simposium")
    val tipoParticipacion= arrayOf("Asistente","Presentante")
    val afectaLinea= arrayOf("SI","NO")
    val tipoPartNI= arrayOf("Nacional","Internacional")
    var listaprofesores=ArrayList<String>()
    var idSeleccionado =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_evento)
        val spinner= findViewById<Spinner>(R.id.SeleccionarTipoEvento)
        var arrayAdapter=
            ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,tipoEvento)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val spinner2= findViewById<Spinner>(R.id.SeleccionarParticipacion)
        var arrayAdapter2=
            ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,tipoParticipacion)
        spinner2.adapter = arrayAdapter2
        spinner2.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val spinner3= findViewById<Spinner>(R.id.SeleccionarTipoParticipacion)
        var arrayAdapter3=
            ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,tipoPartNI)
        spinner3.adapter = arrayAdapter3
        spinner3.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val spinner4= findViewById<Spinner>(R.id.AfectaLinea)
        var arrayAdapter4=
            ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,afectaLinea)
        spinner4.adapter = arrayAdapter4
        spinner4.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService11: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado11: Call<Profesores> = apiService11.existeProfesor(message.toInt())
        resultado11.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@AgregarEvento_activity,"Error", Toast.LENGTH_LONG).show()
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
                            var arrayAdapter=ArrayAdapter<String>(this@AgregarEvento_activity,android.R.layout.simple_spinner_item,listaprofesores)
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
    fun SelectFechaInicioEvento(view: View?){
        val fecha = TomarFecha { year, mes, day -> mostrarResultado(year, mes, day) }
        fecha.show(supportFragmentManager,"date picker")
    }
    fun SelectFechaFinalEvento(view: View?){
        val fecha = TomarFecha { year, mes, day -> mostrarResultado2(year, mes, day) }
        fecha.show(supportFragmentManager,"date picker")
    }

    private fun mostrarResultado(year: Int, mes: Int, day: Any) {
        Fechainicio.text="$year-$mes-$day"

    }
    private fun mostrarResultado2(year: Int, mes: Int, day: Any) {
        FechaFinal.text="$year-$mes-$day"

    }

    fun altaevento(view: View) {
        val spinner= findViewById<Spinner>(R.id.SeleccionarTipoEvento)
        val spinner2= findViewById<Spinner>(R.id.SeleccionarParticipacion)
        val spinner3= findViewById<Spinner>(R.id.SeleccionarTipoParticipacion)
        val spinner4= findViewById<Spinner>(R.id.AfectaLinea)
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val e= eventos(idSeleccionado,0,spinner.getSelectedItem().toString(),NombreEventoEditText.text.toString(),spinner2.getSelectedItem().toString(),spinner4.getSelectedItem().toString(),spinner3.getSelectedItem().toString(),TituloEventoEditText.text.toString(),Fechainicio.text.toString(),FechaFinal.text.toString(),ComprobanteEditText.text.toString())
        println(e)
        val resultado: Call<eventos> = apiService.insertarEvento(e)
        resultado.enqueue(object : Callback<eventos>
        {
            override fun onFailure(call: Call<eventos>, t: Throwable)
            {
                println("Fallo")
                Toast.makeText(this@AgregarEvento_activity,"Error Registro", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<eventos>, response: Response<eventos>)
            {
                Toast.makeText(this@AgregarEvento_activity,"Evento Registrado!!", Toast.LENGTH_LONG).show()
                println("Exito")
                println(response.isSuccessful)
                println(response.code())
            }
        })
    }

}