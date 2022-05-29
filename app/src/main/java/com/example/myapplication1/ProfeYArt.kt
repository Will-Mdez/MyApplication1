package com.example.myapplication1

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_profe_yart.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProfeYArt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message: String = intent.getStringExtra("idUsuario").toString()
        setContentView(R.layout.activity_profe_yart)



    }

    fun BuscarArticulos(view: View?){
        val este=this;
        val message: String = intent.getStringExtra("idUsuario").toString()
        val apiService: APIService = RestEngine.getRestEngine().create(APIService::class.java)
        val fecha1: String= Fechainicio.text.toString()
        val fecha2:String=FechaFinal.toString()
        val resultado: Call<List<Articulo>> = apiService.tablaarticulos(message.toInt(),Fechainicio.text.toString(),FechaFinal.text.toString())

        resultado.enqueue(object : Callback<List<Articulo>>
        {
            override fun onFailure(call: Call<List<Articulo>>, t: Throwable)
            {
                Toast.makeText(this@ProfeYArt,"Error", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<Articulo>>, response: Response<List<Articulo>>)
            {
                Toast.makeText(this@ProfeYArt,"Ok", Toast.LENGTH_LONG).show()
                print(response.body().toString())
                Nombre11.text=response.body()!![0].titulo
                TipoCRL12.text=response.body()!![0].tipoCRL
                TipoNI13.text=response.body()!![0].tipoNI

                if(response.body()!![0].autores.size ==2){
                    var nombre1:String=response.body()!![0].autores[0].nombreProfesor+" "+response.body()!![0].autores[0].apellidoPaterno+" "+response.body()!![0].autores[0].apellidoMaterno
                    val nombre2:String=response.body()!![0].autores[1].nombreProfesor+" "+response.body()!![0].autores[1].apellidoPaterno+" "+response.body()!![0].autores[1].apellidoMaterno
                    Profesores14.text=nombre1+"\n"+nombre2
                    Posicion15.text=response.body()!![0].autores[0].pos.toString()+"\n\n"+response.body()!![0].autores[1].pos.toString()
                    Validado16.text=response.body()!![0].autores[0].validado.toString()+"\n"+response.body()!![0].autores[1].validado.toString()
                }else   {
                    Profesores14.text=response.body()!![0].autores[0].nombreProfesor+" "+response.body()!![0].autores[0].apellidoPaterno+" "+response.body()!![0].autores[0].apellidoMaterno
                    Posicion15.text=response.body()!![0].autores[0].pos.toString()
                    Validado16.text=response.body()!![0].autores[0].validado.toString()
                }
                Fecha16.text=response.body()!![0].fechaedicion

                //Articulo 2
                if(response.body()!!.size==2) {
                    Nombre21.text = response.body()!![1].titulo
                    TipoCRL22.text = response.body()!![1].tipoCRL
                    TipoNI23.text = response.body()!![1].tipoNI
                    if (response.body()!![1].autores.size == 2) {
                        var nombre1: String = response.body()!![1].autores[0].nombreProfesor + " " + response.body()!![1].autores[0].apellidoPaterno + " " + response.body()!![1].autores[0].apellidoMaterno
                        val nombre2: String =response.body()!![1].autores[1].nombreProfesor + " " + response.body()!![1].autores[1].apellidoPaterno + " " + response.body()!![1].autores[1].apellidoMaterno
                        Profesores24.text = nombre1 + "\n" + nombre2
                        Posicion25.text =response.body()!![1].autores[0].pos.toString() + "\n\n" + response.body()!![1].autores[1].pos.toString()
                        Validado26.text =response.body()!![1].autores[0].validado.toString() + "\n" + response.body()!![1].autores[1].validado.toString()
                    } else {
                        Profesores24.text =response.body()!![1].autores[0].nombreProfesor + " " + response.body()!![1].autores[0].apellidoPaterno + " " + response.body()!![1].autores[0].apellidoMaterno
                        Posicion25.text = response.body()!![1].autores[0].pos.toString()
                        Validado26.text = response.body()!![1].autores[0].validado.toString()
                    }
                    Fecha36.text = response.body()!![1].fechaedicion
                }

                //Articulo 3
                if(response.body()!!.size==3) {
                    Nombre31.text = response.body()!![2].titulo
                    TipoCRL32.text = response.body()!![2].tipoCRL
                    TipoNI33.text = response.body()!![2].tipoNI
                    if (response.body()!![2].autores.size == 2) {
                        var nombre1: String =
                            response.body()!![2].autores[0].nombreProfesor + " " + response.body()!![2].autores[0].apellidoPaterno + " " + response.body()!![2].autores[0].apellidoMaterno
                        val nombre2: String =
                            response.body()!![2].autores[1].nombreProfesor + " " + response.body()!![2].autores[1].apellidoPaterno + " " + response.body()!![2].autores[1].apellidoMaterno
                        Profesores34.text = nombre1 + "\n" + nombre2
                        Posicion35.text =
                            response.body()!![2].autores[0].pos.toString() + "\n\n" + response.body()!![2].autores[1].pos.toString()
                        Validado36.text =
                            response.body()!![2].autores[0].validado.toString() + "\n" + response.body()!![2].autores[1].validado.toString()
                    } else {
                        Profesores34.text =
                            response.body()!![2].autores[0].nombreProfesor + " " + response.body()!![2].autores[0].apellidoPaterno + " " + response.body()!![2].autores[0].apellidoMaterno
                        Posicion35.text = response.body()!![2].autores[0].pos.toString()
                        Validado36.text = response.body()!![2].autores[0].validado.toString()
                    }
                    Fecha36.text = response.body()!![2].fechaedicion
                }

                //Articulo 4
                if(response.body()!!.size==4) {
                    Nombre41.text = response.body()!![3].titulo
                    TipoCRL42.text = response.body()!![3].tipoCRL
                    TipoNI43.text = response.body()!![3].tipoNI
                    if (response.body()!![3].autores.size == 2) {
                        var nombre1: String =
                            response.body()!![3].autores[0].nombreProfesor + " " + response.body()!![3].autores[0].apellidoPaterno + " " + response.body()!![3].autores[0].apellidoMaterno
                        val nombre2: String =
                            response.body()!![3].autores[1].nombreProfesor + " " + response.body()!![3].autores[1].apellidoPaterno + " " + response.body()!![3].autores[1].apellidoMaterno
                        Profesores44.text = nombre1 + "\n" + nombre2
                        Posicion45.text =
                            response.body()!![3].autores[0].pos.toString() + "\n\n" + response.body()!![3].autores[1].pos.toString()
                        Validado46.text =
                            response.body()!![3].autores[0].validado.toString() + "\n" + response.body()!![3].autores[1].validado.toString()
                    } else {
                        Profesores44.text =
                            response.body()!![3].autores[0].nombreProfesor + " " + response.body()!![3].autores[0].apellidoPaterno + " " + response.body()!![3].autores[0].apellidoMaterno
                        Posicion45.text = response.body()!![3].autores[0].pos.toString()
                        Validado46.text = response.body()!![3].autores[0].validado.toString()
                    }
                    Fecha46.text = response.body()!![3].fechaedicion
                }


                //Articulo 5
                if(response.body()!!.size==5) {
                    Nombre51.text = response.body()!![4].titulo
                    TipoCRL52.text = response.body()!![4].tipoCRL
                    TipoNI53.text = response.body()!![4].tipoNI
                    if (response.body()!![4].autores.size == 2) {
                        var nombre1: String =
                            response.body()!![4].autores[0].nombreProfesor + " " + response.body()!![4].autores[0].apellidoPaterno + " " + response.body()!![4].autores[0].apellidoMaterno
                        val nombre2: String =
                            response.body()!![4].autores[1].nombreProfesor + " " + response.body()!![4].autores[1].apellidoPaterno + " " + response.body()!![4].autores[1].apellidoMaterno
                        Profesores54.text = nombre1 + "\n" + nombre2
                        Posicion55.text =
                            response.body()!![4].autores[0].pos.toString() + "\n\n" + response.body()!![4].autores[1].pos.toString()
                        Validado56.text =
                            response.body()!![4].autores[0].validado.toString() + "\n" + response.body()!![4].autores[1].validado.toString()
                    } else {
                        Profesores54.text =
                            response.body()!![4].autores[0].nombreProfesor + " " + response.body()!![4].autores[0].apellidoPaterno + " " + response.body()!![4].autores[0].apellidoMaterno
                        Posicion55.text = response.body()!![4].autores[0].pos.toString()
                        Validado56.text = response.body()!![4].autores[0].validado.toString()
                    }
                    Fecha56.text = response.body()!![4].fechaedicion
                }
            }

        })
    }

    fun SelectFechaInicio(view: View?){
        val fecha = TomarFecha{year,mes,day->mostrarResultado(year,mes,day)}
        fecha.show(supportFragmentManager,"date picker")
    }
    fun SelectFechaFinal(view: View?){
        val fecha = TomarFecha{year,mes,day->mostrarResultado2(year,mes,day)}
        fecha.show(supportFragmentManager,"date picker")
    }

    private fun mostrarResultado(year: Int, mes: Int, day: Any) {
        Fechainicio.text="$year-$mes-$day"

    }
    private fun mostrarResultado2(year: Int, mes: Int, day: Any) {
        FechaFinal.text="$year-$mes-$day"

    }

    class TomarFecha(val listener:(year:Int, mes:Int,day:Int)->Unit):DialogFragment(),DatePickerDialog.OnDateSetListener{

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