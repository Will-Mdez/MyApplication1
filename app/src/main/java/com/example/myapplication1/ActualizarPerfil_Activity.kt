package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_actualizar_perfil.*
import kotlinx.android.synthetic.main.activity_generales.*
import kotlinx.android.synthetic.main.activity_registro_profesor.*
import kotlinx.android.synthetic.main.activity_registro_profesor.ApellidoMaternoEditText
import kotlinx.android.synthetic.main.activity_registro_profesor.ApellidoPaternoEditText
import kotlinx.android.synthetic.main.activity_registro_profesor.GradoEditText
import kotlinx.android.synthetic.main.activity_registro_profesor.NombreEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActualizarPerfil_Activity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val ListaIntitutos= arrayOf("Selecciona instituto","Computacion","Electronica y Mecatronica","Fisica y Matematicas")
    val ListaRangos= arrayOf("Selecciona Rol","Rector","Viscerector","Jefe de Carrera","Profesor")
    val ListaCarreras=arrayOf("Seleccionar Carrera","Ingenieria Computacion","Ingenieria Electronica","Lic. Matematicas")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_perfil)
        //Valor que se va a pasar
        val message: String = intent.getStringExtra("idUsuario").toString()

        val ruta_url="http://192.168.1.77:3000/img/id"+message+".png"
        Picasso.get().load(ruta_url).into(fotoActualizarImagen)

        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado11: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado11.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                //Toast.makeText(this@ActualizarPerfil_Activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {

                NombreEditText.setText(response.body()!!.nombreProfesor.toString())
                ApellidoPaternoEditText.hint=response.body()!!.apellidoPaterno.toString()
                ApellidoMaternoEditText.hint=response.body()!!.apellidoMaterno
                GradoEditText.hint=response.body()!!.grado
                val IdCarrera = response.body()!!.idCarrera.toInt()
                GradoEditText.hint=response.body()!!.grado.toString()
                val resultado: Call<Institutos> = apiService.listOneInstituto(response.body()!!.idInstituto.toInt())
                resultado.enqueue(object : Callback<Institutos>
                {
                    override fun onFailure(call: Call<Institutos>, t: Throwable)
                    {
                        //Toast.makeText(this@ActualizarPerfil_Activity,"Error", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<Institutos>, response: Response<Institutos>)
                    {
                        //Toast.makeText(this@ActualizarPerfil_Activity,"Ok", Toast.LENGTH_LONG).show()
                    }
                })
                val resultado22 : Call<Carrera> = apiService.listOneCarreras(IdCarrera)
                resultado22.enqueue(object : Callback<Carrera>
                {
                    override fun onFailure(call: Call<Carrera>, t: Throwable)
                    {
                        //Toast.makeText(this@ActualizarPerfil_Activity,"Error", Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<Carrera>, response: Response<Carrera>)
                    {
                        //Toast.makeText(this@ActualizarPerfil_Activity,"Ok", Toast.LENGTH_LONG).show()
                    }
                })
            }

        })

        ///////////////////

        val spinner= findViewById<Spinner>(R.id.SeleccionarInstituto)
        var arrayAdapter=
            ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ListaIntitutos)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                Toast.makeText(applicationContext,"instituto:"+ListaIntitutos[pos],Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val spinnerRango= findViewById<Spinner>(R.id.SeleccionarRango)
        var arrayAdapterRango=
            ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ListaRangos)
        spinnerRango.adapter = arrayAdapterRango
        spinnerRango.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                Toast.makeText(applicationContext,"Rango:"+ListaRangos[pos],Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val spinnerCarrera= findViewById<Spinner>(R.id.SeleccionarCarrera)
        val arrayAdapterCarrera=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ListaCarreras)
        spinnerCarrera.adapter = arrayAdapterCarrera
        spinnerCarrera.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                Toast.makeText(applicationContext,"selected Item:"+ListaCarreras[pos],Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun actualizarPerfil(view: View){
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val spinner: Spinner = findViewById(R.id.SeleccionarInstituto)
        spinner.onItemSelectedListener = this
        val spinnerRango: Spinner = findViewById(R.id.SeleccionarRango)
        spinnerRango.onItemSelectedListener = this
        val spinnerCarrera: Spinner = findViewById(R.id.SeleccionarCarrera)
        spinnerCarrera.onItemSelectedListener = this
        var idInst=0
        if(spinner.getSelectedItem().toString()=="Computacion"){
            idInst=1
        }else if(spinner.getSelectedItem().toString()=="Electronica y Mecatronica"){
            idInst=2
        }else if(spinner.getSelectedItem().toString()=="Fisica y Matematicas"){
            idInst=3
        }
        var idCarr=0
        if(spinnerCarrera.getSelectedItem().toString()=="Ingenieria Computacion"){
            idCarr=1
        }else if(spinnerCarrera.getSelectedItem().toString()=="Ingenieria Electronica"){
            idCarr=2
        }else if(spinnerCarrera.getSelectedItem().toString()=="Lic. Matematicas"){
            idCarr=3
        }

        val apiService:APIService=RestEngine.getRestEngine().create(APIService::class.java)
        val p=Profesores(message.toInt(),NombreEditText.text.toString(),ApellidoPaternoEditText.text.toString(),ApellidoMaternoEditText.text.toString(),GradoEditText.text.toString(),1,1,spinnerRango.getSelectedItem().toString())
        //val p=Profesores(0,"Temporalcell","easd","asdasd","asd",1,1,"asd")
        println(p)
        val resultado: Call<Profesores> = apiService.actualizarProfesor(p,message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                println("Fallo Actualizacion")
                Toast.makeText(this@ActualizarPerfil_Activity,"Error Actualizado", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                Toast.makeText(this@ActualizarPerfil_Activity,"Actualizado!!", Toast.LENGTH_LONG).show()
                println("Exito Actualizacion")
                println(response.isSuccessful)
                println(response.code())
            }
        })
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}