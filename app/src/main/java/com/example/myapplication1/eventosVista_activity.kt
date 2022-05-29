package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.adapter.ProfesorAdapter
import com.example.myapplication1.adapter.eventosAdapter
import kotlinx.android.synthetic.main.activity_eventos_vista.*
import kotlinx.android.synthetic.main.activity_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class eventosVista_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message: String = intent.getStringExtra("idUsuario").toString()
        setContentView(R.layout.activity_eventos_vista)
        var id=message.toInt()
        initRecyclerView(id)

    }
    fun initRecyclerView(int: Int) {
        //Obtener Profesores
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService11: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val resultado11: Call<Profesores> = apiService11.existeProfesor(message.toInt())
        resultado11.enqueue(object : Callback<Profesores>
        {
            override fun onFailure(call: Call<Profesores>, t: Throwable)
            {
                Toast.makeText(this@eventosVista_activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                if(response.body()!!.rol=="Profesor"){
                    testo.setText("")
                    val spinner= findViewById<Spinner>(R.id.SeleccionarProfesor)
                    spinner.isInvisible
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerEventosV)
                    recyclerView.layoutManager = LinearLayoutManager(this@eventosVista_activity)
                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                    val resultado: Call<List<eventos>> = apiService.listeventosProfesor(int)
                    resultado.enqueue(object : Callback<List<eventos>> {
                        override fun onFailure(call: Call<List<eventos>>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<List<eventos>>, response: Response<List<eventos>>){
                            println(response.body()!!)
                            recyclerView.adapter = eventosAdapter(response.body()!!,this@eventosVista_activity)
                        }
                    })
                }else{
                    //Seleccionar Profsor

                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                    val resultado: Call<List<Profesores>> = apiService.listprofesoresporinstituto(response.body()!!.idInstituto)
                    resultado.enqueue(object : Callback<List<Profesores>> {
                        override fun onFailure(call: Call<List<Profesores>>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<List<Profesores>>, response: Response<List<Profesores>>){
                            var listaprofesores=ArrayList<String>()
                            for (i in 0 .. response.body()!!.size-1){
                                listaprofesores.add(response.body()!![i].nombreProfesor+" "+response.body()!![i].apellidoPaterno)
                            }
                            println(listaprofesores)

                            val spinner= findViewById<Spinner>(R.id.SeleccionarProfesor)
                            var arrayAdapter=ArrayAdapter<String>(this@eventosVista_activity,android.R.layout.simple_spinner_item,listaprofesores)
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinner.adapter = arrayAdapter
                            spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                                override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerEventosV)
                                    recyclerView.layoutManager = LinearLayoutManager(this@eventosVista_activity)
                                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                                    val resultado: Call<List<eventos>> = apiService.listeventosProfesor(response.body()!![pos].idProfesor)
                                    resultado.enqueue(object : Callback<List<eventos>> {
                                        override fun onFailure(call: Call<List<eventos>>, t: Throwable) {
                                        }
                                        override fun onResponse(call: Call<List<eventos>>, response: Response<List<eventos>>){
                                            println(response.body()!!)
                                            recyclerView.adapter = eventosAdapter(response.body()!!,this@eventosVista_activity)
                                        }
                                    })
                                }
                                override fun onNothingSelected(p0: AdapterView<*>?) {
                                    TODO("Not yet implemented")
                                }
                            }

                        }
                    })
                    

                }

            }

        })

    }
}