package com.example.myapplication1

import java.util.ArrayList

class usuarios : ArrayList<usuario>()
data class usuario(
    val idUsuarios: Int,
    val correo: String,
    val password: String
)
