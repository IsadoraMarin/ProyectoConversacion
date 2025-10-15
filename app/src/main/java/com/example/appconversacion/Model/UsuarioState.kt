package com.example.appconversacion.Model


data class UsuarioState(
    val nombre: String = " ",
    val correo: String = " ",
    val clave: String = " ",
    val direccion: String = " ",
    val aceptarTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores()
)