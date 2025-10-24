package com.example.appconversacion.ui.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appconversacion.ViewModel.UsuarioViewModel

@Composable
fun ResumenScreen(viewModel: UsuarioViewModel){
    val estado by viewModel.estado.collectAsState()

    Column(Modifier.padding(all = 16.dp)){
        Text(text = "Resumen de registro", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Nombre: ${estado.nombre}")
        Text(text = "Correo: ${estado.correo}")
        Text(text = "Contraseña: ${"*".repeat(n = estado.clave.length)}")
        Text(text = "Terminos: ${if (estado.aceptarTerminos) "Aceptados" else "No los acepto"}")
    }
}