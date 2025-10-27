package com.example.appconversacion.Model

//Clase para ver el estado de usuario
data class UsuarioState(
    val nombre: String = " ",
    val correo: String = " ",
    val clave: String = " ",
    val aceptarTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores()
)
