package com.example.appconversacion.navigation




import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appconversacion.ui.Screens.HomeScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appconversacion.ui.Screens.RegistroScreen
import com.example.appconversacion.ViewModel.UsuarioViewModel
import com.example.appconversacion.ui.Screens.ResumenScreen


@Composable
fun AppConversacion() {
    val navController = rememberNavController()


    val usuarioViewModel: UsuarioViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home_screen") {


        composable("home_screen") {
            HomeScreen(navController = navController)
        }

        composable("registro_screen"){

            RegistroScreen(navController = navController, viewModel = usuarioViewModel)
        }

        composable("Resumen"){
            ResumenScreen(viewModel = usuarioViewModel)
        }
    }
}

