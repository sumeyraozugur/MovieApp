package com.sumeyra.data.di

import com.sumeyra.data.sevice.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created on 5.01.2026
 * @author Sümeyra Özuğur
 */

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}