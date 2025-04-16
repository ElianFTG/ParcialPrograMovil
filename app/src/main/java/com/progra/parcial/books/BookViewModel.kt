package com.progra.parcial.books

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.progra.data.NetworkResult
import com.progra.domain.Book
import com.progra.usecases.FindBooks
import com.progra.usecases.GetBooksLike
import com.progra.usecases.SaveBook
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val findBooks: FindBooks,
    private val getBooksLike: GetBooksLike,
    private val saveBook: SaveBook,
    @ApplicationContext private val context: Context
) : ViewModel() {

    sealed class BookState {
        object Init : BookState()
        data class SuccessList(val model: List<Book>) : BookState()
        data class SuccessLike(val message: String) : BookState()
        data class Error(val message: String) : BookState()
    }

    private val _state = MutableStateFlow<BookState>(BookState.Init)
    val state: StateFlow<BookState> = _state

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _likeMessage = MutableStateFlow<String?>(null)
    val likeMessage: StateFlow<String?> = _likeMessage

    private val _likedBooks = MutableStateFlow<List<Book>>(emptyList())
    val likedBooks: StateFlow<List<Book>> = _likedBooks

    fun searchBooks(title: String) {
        _state.value = BookState.Init
        viewModelScope.launch {
            when (val result = findBooks.invoke(title)) {
                is NetworkResult.Success -> {
                    _books.value = result.data
                }
                is NetworkResult.Error -> {
                    _state.value = BookState.Error(result.error)
                }
            }
        }
    }

    fun likedBook(book: Book) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    saveBook.invoke(book)
                    _likeMessage.value = "Se añadió el libro ${book.title} a su colección"
                }
            } catch (e: Exception) {
                _likeMessage.value = "Error al dar like: ${e.message}"
            }
        }
    }

    fun getLikedBooks() {
        viewModelScope.launch() {
            try {
                withContext(Dispatchers.IO) {
                    val books = getBooksLike.invoke()
                    _likedBooks.value = books
                    _state.value = BookState.Init
                }
            } catch (e: Exception) {
                _state.value = BookState.Error("Error al mostrar los libros con like: ${e.message}")
            }
        }
    }

    fun clearMessage() {
        _state.value = BookState.Init
    }
}
