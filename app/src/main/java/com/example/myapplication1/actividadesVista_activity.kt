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
import com.example.myapplication1.adapter.actividadesAdapter
import com.example.myapplication1.adapter.eventosAdapter
import kotlinx.android.synthetic.main.activity_actividades_vista.*
import kotlinx.android.synthetic.main.activity_eventos_vista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class actividadesVista_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_vista)
        val message: String = intent.getStringExtra("idUsuario").toString()
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
                Toast.makeText(this@actividadesVista_activity,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<Profesores>, response: Response<Profesores>)
            {
                if(response.body()!!.rol=="Profesor"){
                    testo2.setText("")
                    val spinner= findViewById<Spinner>(R.id.SeleccionarProfesorAct)
                    spinner.isInvisible
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerActividadesV)
                    recyclerView.layoutManager = LinearLayoutManager(this@actividadesVista_activity)
                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                    val resultado: Call<List<actividades>> = apiService.listactividadesProfesor(int)
                    resultado.enqueue(object : Callback<List<actividades>> {
                        override fun onFailure(call: Call<List<actividades>>, t: Throwable) {
                        }
                        override fun onResponse(call: Call<List<actividades>>, response: Response<List<actividades>>){
                            println(response.body()!!)
                            recyclerView.adapter = actividadesAdapter(response.body()!!,this@actividadesVista_activity)
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

                            val spinner= findViewById<Spinner>(R.id.SeleccionarProfesorAct)
                            var arrayAdapter= ArrayAdapter<String>(this@actividadesVista_activity,android.R.layout.simple_spinner_item,listaprofesores)
                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinner.adapter = arrayAdapter
                            spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
                                override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerActividadesV)
                                    recyclerView.layoutManager = LinearLayoutManager(this@actividadesVista_activity)
                                    val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
                                    val resultado: Call<List<actividades>> = apiService.listactividadesProfesor(response.body()!![pos].idProfesor)
                                    resultado.enqueue(object : Callback<List<actividades>> {
                                        override fun onFailure(call: Call<List<actividades>>, t: Throwable) {
                                        }
                                        override fun onResponse(call: Call<List<actividades>>, response: Response<List<actividades>>){
                                            println(response.body()!!)
                                            recyclerView.adapter = actividadesAdapter(response.body()!!,this@actividadesVista_activity)
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