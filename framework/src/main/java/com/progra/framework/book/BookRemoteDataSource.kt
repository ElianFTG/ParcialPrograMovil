package com.progra.framework.book

import com.progra.data.NetworkResult
import com.progra.data.book.IBookRemoteDataSource
import com.progra.domain.Book
import com.progra.framework.mappers.toModel
import com.progra.framework.service.RetrofitBuilder

class BookRemoteDataSource(val retrofiService: RetrofitBuilder) : IBookRemoteDataSource{
    override suspend fun fetchBooks(title: String): NetworkResult<List<Book>> {
        val response = retrofiService.apiService.getBooksRemote(title)
        if(response.isSuccessful) {
            return NetworkResult.Success(response.body()!!.toModel())
        } else {
            return NetworkResult.Error(response.message())
        }
    }
}