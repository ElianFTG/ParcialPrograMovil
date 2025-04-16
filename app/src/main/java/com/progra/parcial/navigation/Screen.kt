package com.progra.parcial.navigation

sealed class Screen(val route: String) {
    object BooksScreen: Screen("books")
}