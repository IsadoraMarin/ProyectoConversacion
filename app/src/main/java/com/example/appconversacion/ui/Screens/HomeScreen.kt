package com.example.appconversacion.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appconversacion.R
import com.example.appconversacion.Model.PostData
import com.example.appconversacion.ui.theme.AppConversacionTheme
import org.jetbrains.annotations.Unmodifiable
import androidx.compose.material.icons.filled.Person

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun HomeScreen(navController: NavController){
    val posts = listOf(
        PostData(1, "Usuario 1", "¿Vale la pena comprar el DLC de (Juego nuevo)?", "Videojuego 1", R.drawable.ic_launcher_background, 15),
        PostData(2, "Usuario 2", "¿Que ofertas hay en Steam?", "Videojuego 2", R.drawable.ic_launcher_background, 38),
        PostData(3, "Usuario 3", "Videojuegos dificiles de completar", "Videojuego 3", R.drawable.ic_launcher_background, 8),
        PostData(4, "Usuario 4", "¿Que juegos nuevos tienen?", "Conversacion", R.drawable.ic_launcher_background, 35)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Foro Videojuegos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate("registro_screen")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Ir a registro"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            //Pense que decia TODO de bueno, TODO de absoluto, pero es TO DO
            //Esta parte tal vez sea la que sirva para poder crear nuevos posts
            //Esta es
            FloatingActionButton(onClick = {
                navController.navigate("gestionar_post_screen")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Crear un nuevo Post")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(posts) { postItem ->
                PostCard(post = postItem, onClick = {

                    navController.navigate("post_detail/${postItem.id}")
                })
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PostCard(post: PostData, onClick: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = post.imagenAutor),
                contentDescription = "Foto de autor",
                modifier = Modifier
                    .size(48.dp)
                    .clip((MaterialTheme.shapes.small)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)){
                Text(text = post.titulo, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "En ${post.videojuego} por ${post.autor}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.End){
                Text(text = "${post.respuestas}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "Respuestas", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    AppConversacionTheme {
        HomeScreen(navController = rememberNavController())
    }
}