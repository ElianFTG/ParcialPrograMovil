package com.progra.parcial.di

import android.content.Context
import com.progra.data.BookRepository
import com.progra.data.book.ILocalDataSource
import com.progra.data.book.IBookRemoteDataSource
import com.progra.framework.book.BookLocalDataSource
import com.progra.framework.book.BookRemoteDataSource
import com.progra.framework.service.IApiService
import com.progra.framework.service.RetrofitBuilder
import com.progra.usecases.SaveBook
import com.progra.usecases.GetBooksLike
import com.progra.usecases.FindBooks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerRetrofitBuilder(@ApplicationContext context: Context) : RetrofitBuilder {
        return RetrofitBuilder(context)
    }

    @Provides
    @Singleton
    fun provideBookRemoteDataSource(retrofitClient: RetrofitBuilder): IBookRemoteDataSource {
        return BookRemoteDataSource(retrofitClient)
    }

    @Provides
    @Singleton
    fun provideBookLocalDataSource(@ApplicationContext context: Context) : ILocalDataSource {
        return BookLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideBookRepository(remoteDataSource: IBookRemoteDataSource, localDataSource: ILocalDataSource): BookRepository {
        return BookRepository(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideSearchBooks(book: BookRepository): FindBooks {
        return FindBooks(book)
    }

    @Provides
    @Singleton
    fun provideSaveBook(repository: BookRepository): SaveBook {
        return SaveBook(repository)
    }

    @Provides
    @Singleton
    fun provideGetMyFavoriteBooks(repository: BookRepository): GetBooksLike {
        return GetBooksLike(repository)
    }
}