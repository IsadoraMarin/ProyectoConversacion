package com.example.appconversacion.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appconversacion.ViewModel.PostViewModel
import com.example.appconversacion.ViewModel.UsuarioViewModel
import com.example.appconversacion.ui.Screens.*

@Composable
fun AppConversacion() {
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val postViewModel: PostViewModel = viewModel()

    val postState by postViewModel.uiEstado.collectAsState()

    NavHost(navController = navController,
        startDestination = "home_screen") {

        composable("home_screen"){
            HomeScreen(navController = navController)
        }

        composable("registro_screen"){
            RegistroScreen(navController = navController, viewModel = usuarioViewModel)
        }

        composable("gestionar_post_screen") {
            // Asumiendo que tienes una pantalla para esto, como GestionarPostScreen.kt
            GestionarPostScreen(
                viewModel = postViewModel,
                postId = null, // Se pasa null porque es para CREAR, no para editar
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = "gestionar_post_screen/{postId}",
            arguments = listOf(navArgument("postId") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("postId")
            GestionarPostScreen(
                viewModel = postViewModel,
                postId = postId, // Aquí se pasa el ID para editar
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable("Resumen"){
            ResumenScreen(viewModel = usuarioViewModel)
        }
    }
}
