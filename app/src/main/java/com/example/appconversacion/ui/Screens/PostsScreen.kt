package com.example.appconversacion.ui.Screens

import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appconversacion.ViewModel.PostViewModel
import com.example.appconversacion.Model.PostData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    viewModel: PostViewModel,
    onNavigateToCreate: () -> Unit, //Lo mismo que en gestionar PostScreen
    onNavigateToEdit: (Int) -> Unit
){
    val uiState by viewModel.uiEstado.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Foro Videojuegos conversacion")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(Icons.Default.Add, contentDescription = "Crear Conversacion")
            }
        }
    ){ paddingValues ->
        if(uiState.posts.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ){
                Text("Todavia no inicias una conversacion ¡Rompe el hielo!")
            }
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                items(uiState.posts){ post ->
                    PostItem(
                        post = post,
                        onEdit = {onNavigateToEdit(post.id)},
                        onDelete = {viewModel.borrarPost(post.id)}
                    )
                }
            }
        }
    }
}

@Composable
//La idea de usar Unit en esta parte es que se mantenga reutilizable y limpio, siendo que Unit no tma los valores y solo ejecuta
fun PostItem(post: PostData, onEdit: () -> Unit, onDelete: () -> Unit) {
    //En este caso, solo le dice que estos botones funcionen cuando se opriman los botones
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.titulo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "en ${post.videojuego}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Publicado por: ${post.autor}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${post.respuestas} respuestas",
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Borrar")

                    //Aqui es donde se llaman a las funciones
                }
            }
        }
    }
}
