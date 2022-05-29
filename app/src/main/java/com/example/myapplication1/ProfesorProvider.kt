package com.example.login

import com.example.myapplication1.Profesores

class ProfesorProvider {
    companion object
    {
        val profesorList= listOf<Profesores>(
            Profesores(
                7,
                "Erik",
                "Ramos",
                "Pérez",
                "M.T.C.A.",
                1,
                1,
                "Profesor"
            ),
            Profesores(
                6,
                "William",
                "Mendez",
                "Gonzalez",
                "Std",
                1,
                1,
                "Profesor"
            ),
            Profesores(
                10,
                "Jorge Perales",
                "Narvaez",
                "Guevara",
                "Ing",
                1,
                1,
                "Profesor"
            )
        )
    }
}