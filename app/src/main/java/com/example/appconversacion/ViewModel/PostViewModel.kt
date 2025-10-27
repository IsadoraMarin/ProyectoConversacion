package com.example.appconversacion.ViewModel

import androidx.lifecycle.ViewModel
import com.example.appconversacion.R
import com.example.appconversacion.Model.PostData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Aqui se ve otra lista de los posts
data class PostUiEstado(
    val posts: List<PostData> = emptyList()
)

class PostViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PostUiEstado())
    val uiEstado: StateFlow<PostUiEstado> = _uiState.asStateFlow()

    init {
        _uiState.value = PostUiEstado(
            posts = listOf(
                PostData(1, "Usuario 1", "¿Vale la pena comprar el DLC?", "Videojuego 1", R.drawable.ic_launcher_background, 15),
                PostData(2, "Usuario 2", "¿Qué ofertas hay en Steam?", "Videojuego 2", R.drawable.ic_launcher_background, 38),
                PostData(3, "Usuario 3", "Juegos difíciles de completar", "Videojuego 3", R.drawable.ic_launcher_background, 8),
                PostData(4, "Usuario 4", "¿Qué juegos nuevos tienen?", "Conversación", R.drawable.ic_launcher_background, 35)
            )
        )
    }

    fun crearPost(titulo: String, videojuego: String) {

        val nuevoId = (_uiState.value.posts.maxOfOrNull { it.id } ?: 0) + 1

        val nuevoPost = PostData(
            id = nuevoId,
            autor = "Usuario Nuevo",
            titulo = titulo,
            videojuego = videojuego,
            imagenAutor = R.drawable.ic_launcher_background,
            respuestas = 0
        )


        _uiState.update { estadoActual ->
            estadoActual.copy(posts = estadoActual.posts + nuevoPost)
        }
    }

    // Funcion de borrado (Puedes cambiar las imagenes de imagenAutor y de los otros post)
    fun borrarPost(postId: Int) {
        _uiState.update { estadoActual ->
            val postActualizados = estadoActual.posts.filter { it.id != postId }
            estadoActual.copy(posts = postActualizados)
        }
    }

    //Funcion para actualizar (Muchas variables con POSTS help)
    //
    fun actualizarPost(postId: Int, nuevoTitulo: String, nuevoVideojuego: String){
        _uiState.update { estadoActual ->
            val postsActualizados = estadoActual.posts.map { post ->
                //No funcionaba la variable, se llamaba post id, no post solamente.
                //Habia que renombrarla
                if (post.id == postId){

                    post.copy(titulo = nuevoTitulo, videojuego = nuevoVideojuego) // 4. Corregido el valor de videojuego
                } else {

                    post
                }
            }

            estadoActual.copy(posts = postsActualizados)
        }
    }

}
