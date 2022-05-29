package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro_profesor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroProfesor_Activity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var ListaIntitutos= arrayOf("Computacion","Electronica y Mecatronica","Fisica y Matematicas")
    var ListaRangos= arrayOf("")
    var ListaCarreras=arrayOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_profesor)
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado: Call<Profesores> = apiService.existeProfesor(message.toInt())
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                val rol =response.body()!!.rol
                val idins=response.body()!!.idInstituto
                when(rol){
                    "Vicerector"->{
                        ListaRangos= arrayOf("Director Instituto","Jefe de Carrera","Profesor")
                        rango(ListaRangos,0)
                    }
                    "Director Instituto"->{
                        ListaRangos= arrayOf("Jefe de Carrera","Profesor")
                        rango(ListaRangos,idins)
                    }
                    "Jefe Carrera"->{
                        ListaRangos= arrayOf("Profesor")
                        rango(ListaRangos,idins)
                    }
                }

            }
        })




    }

    private fun rango(rangos: Array<String>, i: Int) {
        val spinnerRango= findViewById<Spinner>(R.id.SeleccionarRango)
        var arrayAdapterRango=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,rangos)
        spinnerRango.adapter = arrayAdapterRango
        spinnerRango.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                val apiService2: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                if (i==0){
                    val resultado2: Call<List<Carrera>> = apiService2.listCarrerasporinstituto(1)
                    resultado2.enqueue(object : Callback<List<Carrera>>
                    {
                        override fun onFailure(call: Call<List<Carrera>>, t: Throwable)
                        {
                        }
                        override fun onResponse(call: Call<List<Carrera>>, response: Response<List<Carrera>>)
                        {
                            when(i){
                                0->{
                                    ListaIntitutos= arrayOf("Computacion","Electronica y Mecatronica","Fisica y Matematicas")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                                1->{
                                    ListaIntitutos= arrayOf("Computacion")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                                2->{
                                    ListaIntitutos= arrayOf("Electronica y Mecatronica")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                                3->{
                                    ListaIntitutos= arrayOf("Fisica y Matematicas")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                            }
                        }
                    })
                }else{
                    val resultado2: Call<List<Carrera>> = apiService2.listCarrerasporinstituto(i)
                    resultado2.enqueue(object : Callback<List<Carrera>>
                    {
                        override fun onFailure(call: Call<List<Carrera>>, t: Throwable)
                        {
                        }
                        override fun onResponse(call: Call<List<Carrera>>, response: Response<List<Carrera>>)
                        {
                            when(i){
                                0->{
                                    ListaIntitutos= arrayOf("Computacion","Electronica y Mecatronica","Fisica y Matematicas")
                                    seleccionInstituto(ListaIntitutos,i)
                                }
                                1->{
                                    ListaIntitutos= arrayOf("Computacion")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                                2->{
                                    ListaIntitutos= arrayOf("Electronica y Mecatronica")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                                3->{
                                    ListaIntitutos= arrayOf("Fisica y Matematicas")
                                    seleccionInstituto(ListaIntitutos, i)
                                }
                            }
                        }
                    })
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }


    private fun seleccionInstituto(listaInstitutos: Array<String>, i: Int) {
        val spinner= findViewById<Spinner>(R.id.SeleccionarInstituto)
        var arrayAdapter=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaInstitutos)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)

                        when(pos){
                            0->{
                                ListaCarreras=arrayOf("Ingenieria Computacion")
                                spiner(ListaCarreras)
                            }
                            1->{
                                ListaCarreras=arrayOf("Ingenieria Electronica","Ingenieria en Mecatronica")
                                spiner(ListaCarreras)
                            }
                            2->{
                                ListaCarreras=arrayOf("Lic. Matematicas", "Ing. Fisica Aplicada")
                                spiner(ListaCarreras)
                            }
                        }

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    private fun spiner(listaCarreras: Array<String>) {
        val spinnerCarrera= findViewById<Spinner>(R.id.SeleccionarCarrera)
        val arrayAdapterCarrera=ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listaCarreras)
        spinnerCarrera.adapter = arrayAdapterCarrera
        spinnerCarrera.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
                Toast.makeText(applicationContext,"selected Item:"+listaCarreras[pos],Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    fun altaProfesor(view: View){

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
        val p=Profesores(0,NombreEditText.text.toString(),ApellidoPaternoEditText.text.toString(),ApellidoMaternoEditText.text.toString(),GradoEditText.text.toString(),idInst,idCarr,spinnerRango.getSelectedItem().toString())
        //val p=Profesores(0,"Temporalcell","easd","asdasd","asd",1,1,"asd")
        println(p)
        val resultado: Call<Profesores> = apiService.insertarProfesor(p)
        resultado.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                println("Fallo")
                Toast.makeText(this@RegistroProfesor_Activity,"Error Registro",Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                Toast.makeText(this@RegistroProfesor_Activity,"Registrado!!",Toast.LENGTH_LONG).show()
                println("Exito")
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