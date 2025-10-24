package com.example.appconversacion.Model
import androidx.annotation.DrawableRes
import com.example.appconversacion.R


data class PostData(
    val id: Int,
    val autor: String,
    val titulo: String,
    val videojuego: String,
    @DrawableRes val imagenAutor: Int,
    val respuestas: Int,
)
