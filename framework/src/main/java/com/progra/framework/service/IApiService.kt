package com.progra.framework.service

import com.progra.framework.dto.BookResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {
    @GET("/search.json")
    suspend fun getBooksRemote(@Query("q") title: String) : Response<BookResponseDto>
}