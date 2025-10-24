package com.example.appconversacion.ui.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.compose.ui.unit.dp
import com.example.appconversacion.ViewModel.PostViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
//Todas estas importaciones las tuve que investigar, te recomiendo que busques en internet si es que hay mas que faciliten el trabajo

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GestionarPostScreen(
    viewModel: PostViewModel,
    postId: Int?, //Basicamente, ese Int esta viendo si se puede editar o no, si es que hay algun cambio
    onNavigateUp: () -> Unit // Unit es otra forma de que la pagina vuelva o haga el cambio de pantalla


) {

    val postExistente = postId?.let { id -> //Esto ejecuta si no es null
        viewModel.uiEstado.value.posts.find { it.id == id } //Va a buscar los posts, si encuentra va a contenter postExistente
    }


    var titulo by remember { mutableStateOf(postExistente?.titulo ?: "") } //Va a buscar el post del mismo titulo
    var videojuego by remember { mutableStateOf(postExistente?.videojuego ?: "") } //Lo mismo aqui
    //Basicamente, remember se utiliza para guardar la informacion aun que no sea necesaria

//Scaffold para que se devuelva la pagina

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (postId == null) "Crear Post" else "Editar Post") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título de la publicación") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = videojuego,
                onValueChange = { videojuego = it },
                label = { Text("Videojuego o Tema") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (postId == null) {
                        viewModel.crearPost(titulo, videojuego)
                    } else {
                        viewModel.actualizarPost(postId, titulo, videojuego)
                    }
                    onNavigateUp()
                },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                enabled = titulo.isNotBlank() && videojuego.isNotBlank()
            ) {
                Text(if (postId == null) "Publicar" else "Guardar Cambios")
            }
        }
    }
}