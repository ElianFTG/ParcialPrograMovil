package com.progra.parcial.books

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay

@Composable
fun BookUI(viewModel: BookViewModel = hiltViewModel()) {
    var titulo by remember { mutableStateOf("") }

    val libros by viewModel.books.collectAsState()
    val likeMessage by viewModel.likeMessage.collectAsState()
    val errorState by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.Black) // Fondo oscuro
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Ingrese el título del libro", color = Color.White) },
            textStyle = TextStyle(color = Color.White)
        )

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onClick = { viewModel.searchBooks(titulo) },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB388FF))
        ) {
            Text("Buscar")
        }

        likeMessage?.let { message ->
            LaunchedEffect(message) {
                delay(2000)
                viewModel.clearMessage()
            }
            Text(
                text = message,
                color = Color.Green,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            if (libros.isNotEmpty()) {
                items(libros) { libro ->
                    var isLiked by remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black // Aquí pones el color que desees
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(
                                text = libro.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Green
                                )
                            )

                            Text(
                                text = libro.authors.joinToString(", "),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Thin,
                                    color =Color.Green
                                )
                            )

                            Text(
                                text = "Año: ${libro.yearPublish}",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Thin,
                                    color = Color.Green
                                )
                            )

                            Button(
                                onClick = {
                                    isLiked = !isLiked
                                    viewModel.likedBook(libro)
                                },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(top = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isLiked) Color(0xFFB388FF) else Color.Gray,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                            ) {
                                Text(if (isLiked) "Liked" else "Like")
                            }
                        }
                    }
                }
            } else {
                item {
                    if (errorState !is BookViewModel.BookState.Error) {
                        Text(
                            "Esperando resultados de búsqueda o no se encontraron libros",
                            color = Color.White
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp)) // Espacio para separarse del borde inferior

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { viewModel.getLikedBooks() },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB388FF))
        ) {
            Text("Ver lista de favoritos")
        }

        if (errorState is BookViewModel.BookState.Error) {
            val errorMessage = (errorState as BookViewModel.BookState.Error).message
            Text(
                text = "Error: $errorMessage",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}