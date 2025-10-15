package com.example.appconversacion.Navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.RegistroScreen
import com.example.myapplication.ui.screen.ResumenScreen
import com.example.myapplication.viewmodel.UsuarioViewModel

@Composable
fun AppNavegacion(){
    fun AppNavegacion() {
        val navController = rememberNavController()

        // 🔥 Aquí creamos el ViewModel una sola vez
        val usuarioViewModel: UsuarioViewModel = viewModel()

        NavHost(
            navController = navController,
            startDestination = "registro"
        ) {
            composable(route = "registro") {
                RegistroScreen(navController, usuarioViewModel)
            }
            composable(route = "resumen") {
                ResumenScreen(usuarioViewModel)
            }
        }
    }
}