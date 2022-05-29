package com.example.myapplication1.AgregaEventoYActividad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.myapplication1.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_actualizar_perfil.*
import kotlinx.android.synthetic.main.activity_actualizar_perfil.ApellidoMaternoEditText
import kotlinx.android.synthetic.main.activity_actualizar_perfil.ApellidoPaternoEditText
import kotlinx.android.synthetic.main.activity_actualizar_perfil.GradoEditText
import kotlinx.android.synthetic.main.activity_actualizar_perfil.NombreEditText
import kotlinx.android.synthetic.main.activity_agregar_actividad.*
import kotlinx.android.synthetic.main.activity_agregar_evento.*
import kotlinx.android.synthetic.main.activity_editar_actividad.*
import kotlinx.android.synthetic.main.activity_editar_actividad.FechaFinal
import kotlinx.android.synthetic.main.activity_editar_actividad.Fechainicio
import kotlinx.android.synthetic.main.activity_editar_evento.*
import kotlinx.android.synthetic.main.activity_editar_evento.ComprobanteEditText
import kotlinx.android.synthetic.main.activity_editar_evento.NombreEventoEditText
import kotlinx.android.synthetic.main.activity_editar_evento.TituloEventoEditText
import kotlinx.android.synthetic.main.activity_registro_profesor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarEvento_activity : AppCompatActivity() {
    val tipoEvento=arrayOf("Congreso","Seminario","Curso","Taller","Simposium")
    val tipoParticipacion= arrayOf("Asistente","Presentante")
    val afectaLinea= arrayOf("SI","NO")
    val tipoPartNI= arrayOf("Nacional","Internacional")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_evento)

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

        val message: String = intent.getStringExtra("idEvento").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<eventos> = apiService.unEvento(message.toInt())
        resultado.enqueue(object : Callback<eventos>
        {
            override fun onFailure(call: Call<eventos>, t: Throwable)
            {
                //Toast.makeText(this@ActualizarPerfil_Activity,"Error", Toast.LENGTH_LONG).show()

            }
            override fun onResponse(call: Call<eventos>, response: Response<eventos>)
            {
                NombreEventoEditText.setText(response.body()!!.nombreEvento)
                TituloEventoEditText.setText(response.body()!!.titulo)
                ComprobanteEditText.setText(response.body()!!.comprobante)
                FechaFinal.setText(response.body()!!.fin)
                Fechainicio.setText(response.body()!!.inicio)
                when(response.body()!!.tipoEvento){
                    "Congreso"->{ spinner.setSelection(0)
                    }
                    "Seminario"->{ spinner.setSelection(1)
                    }
                    "Curso"->{spinner.setSelection(2)
                    }
                    "Taller"->{ spinner.setSelection(3)
                    }
                    "Simposium"->{spinner.setSelection(4)
                    }
                }
                when(response.body()!!.participacion){
                    "Asistente"->{spinner2.setSelection(0)
                    }
                    "Presentante"->{spinner2.setSelection(1)
                    }
                }
                when(response.body()!!.tipoParticipacion){

                }
            }

        })
    }

    fun SelectFechaInicioEventoE(view: View) {
        val fecha = AgregarActividad_activity.TomarFecha { year, mes, day ->mostrarResultado(year,mes,day )}
        fecha.show(supportFragmentManager,"date picker")
    }
    fun SelectFechaFinalEventoE(view: View) {
        val fecha = AgregarActividad_activity.TomarFecha { year, mes, day -> mostrarResultado2(year,mes,day) }
        fecha.show(supportFragmentManager,"date picker")
    }
    private fun mostrarResultado(year: Int, mes: Int, day: Any) {
        Fechainicio.text="$year-$mes-$day"

    }
    private fun mostrarResultado2(year: Int, mes: Int, day: Any) {
        FechaFinal.text="$year-$mes-$day"

    }
    fun actualizarevento(view: View) {
        val spinner= findViewById<Spinner>(R.id.SeleccionarTipoEvento)
        val spinner2= findViewById<Spinner>(R.id.SeleccionarParticipacion)
        val spinner3= findViewById<Spinner>(R.id.SeleccionarTipoParticipacion)
        val spinner4= findViewById<Spinner>(R.id.AfectaLinea)
        val message: String = intent.getStringExtra("idActividad").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val e= eventos(message.toInt(),0,spinner.getSelectedItem().toString(),NombreEventoEditText.text.toString(),spinner2.getSelectedItem().toString(),spinner4.getSelectedItem().toString(),spinner3.getSelectedItem().toString(),TituloEventoEditText.text.toString(),Fechainicio.text.toString(),FechaFinal.text.toString(),ComprobanteEditText.text.toString())
        println(e)
        val resultado: Call<eventos> = apiService.actualizarEvento(e,message.toInt())
        resultado.enqueue(object : Callback<eventos>
        {
            override fun onFailure(call: Call<eventos>, t: Throwable)
            {
                println("Fallo")
            }
            override fun onResponse(call: Call<eventos>, response: Response<eventos>)
            {
                println("Exito")
                println(response.isSuccessful)
                println(response.code())
            }
        })
    }
}