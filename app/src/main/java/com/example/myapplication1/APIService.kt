package com.example.myapplication1

import retrofit2.Call
import retrofit2.http.*

interface APIService {
    @GET("usuarios")
    fun listusuarios(): Call<List<usuario>>

    @GET("usuarios/{usuario}/{password}")
    fun existe(@Path("usuario") usuario:String, @Path("password") password:String):Call<usuario>

    @GET("usuarios/{idProfesor}")
    fun existeProfesor(@Path("idProfesor") idProfesor:Int):Call<Profesores>

    @GET("institutos/{idInstituto}")
    fun listOneInstituto(@Path("idInstituto") idInstituto:Int):Call<Institutos>


    //Carreras /carrerasporinstituto

    @GET("carreras/{idCarrera}")
    fun listOneCarreras(@Path("idCarrera") idCarrera:Int):Call<Carrera>

    @GET("carrerasporinstituto/{idInstituto}")
    fun listCarrerasporinstituto(@Path("idInstituto") idInstituto:Int):Call<List<Carrera>>

    //PROFESORES
    @GET("profesores")
    fun listprofesores(): Call<List<Profesores>>

    @GET("profesores/{idInstituto}")
    fun listprofesoresporinstituto(@Path("idInstituto")idInstituto: Int): Call<List<Profesores>>

    @POST("/api/nuevoprofesor/")
    fun insertarProfesor(@Body profesores: Profesores):Call<Profesores>

    @DELETE("eliminarprofesor/{idP}")
    fun eliminarProfesor(@Path("idP") idP:Int):Call<Profesores>

    @PUT("actualizarprofesor/{idP}")
    fun actualizarProfesor(@Body profesores: Profesores,@Path("idP") idP:Int):Call<Profesores>

    //Eventos

    @GET("unevento/{idEvento}")
    fun unEvento(@Path("idEvento") idEvento:Int):Call<eventos>

    @GET("eventosdeprofesor/{idProfesor}")
    fun listeventosProfesor(@Path("idProfesor") idProfesor:Int): Call<List<eventos>>

    @POST("/api/nuevoevento")
    fun insertarEvento(@Body eventos: eventos):Call<eventos>

    @DELETE("eliminarevento/{idE}")
    fun eliminarEvento(@Path("idE") idE:Int):Call<eventos>

    @PUT("actualizarevento/{idE}")
    fun actualizarEvento(@Body eventos: eventos,@Path("idE") idE:Int):Call<eventos>


    //ARTICVULOS
    @GET("tablaarticulos/{idprofesor}/{fechainicio}/{fechafinal}")
    fun tablaarticulos(@Path("idprofesor")idProfesor: Int,@Path("fechainicio")fechainicio:String,@Path("fechafinal")fechafinal:String):Call<List<Articulo>>
    //


    ////actividades
    @GET("unaactividad/{idActividad}")
    fun unaActividad(@Path("idActividad") idActividad:Int):Call<actividades>

    @GET("actividadesdeprofesor/{idProfesor}")
    fun listactividadesProfesor(@Path("idProfesor") idProfesor:Int): Call<List<actividades>>

    @POST("/api/nuevaactividad")
    fun insertarActividad(@Body actividades: actividades):Call<actividades>

    @DELETE("eliminaractividad/{idA}")
    fun eliminarActividad(@Path("idA") idA:Int):Call<actividades>

    @PUT("actualizaractividad/{idA}")
    fun actualizarActividad(@Body actividades: actividades,@Path("idA") idE:Int):Call<actividades>
}