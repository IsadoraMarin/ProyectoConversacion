package com.example.appconversacion.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController //-->Esto hace que se muestre un teclado
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appconversacion.ViewModel.PostViewModel
import com.example.appconversacion.ViewModel.UsuarioViewModel
import com.example.appconversacion.ui.Screens.*

//Hace que aparezcan las instancias
@Composable
fun AppConversacion() {
    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val postViewModel: PostViewModel = viewModel()

    val postState by postViewModel.uiEstado.collectAsState()

    NavHost(navController = navController,
        startDestination = "home_screen") { //--> Donde inicia

        composable("home_screen"){
            HomeScreen(navController = navController)
        } //HomeScreen

        composable("registro_screen"){
            RegistroScreen(navController = navController, viewModel = usuarioViewModel)
        } //Oagina de registro

        composable("gestionar_post_screen") {
            //Pagina para gestionar post_screen
            GestionarPostScreen(
                viewModel = postViewModel,
                postId = null, // Se pasa null porque es para CREAR, no para editar
                onNavigateUp = { navController.navigateUp() }
            )
        }
// Basicamente todo esto gestiona lo que es devolverse de hacer un post
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
//Pagina de resumen de registro
        composable("Resumen"){
            ResumenScreen(viewModel = usuarioViewModel)
        }
    }
}
