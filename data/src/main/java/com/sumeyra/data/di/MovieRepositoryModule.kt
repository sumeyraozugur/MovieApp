package com.sumeyra.data.di

import com.sumeyra.data.repository.MovieRepositoryImpl
import com.sumeyra.data.sevice.MovieService
import com.sumeyra.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MovieRepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        service: MovieService
    ): MovieRepository = MovieRepositoryImpl(service)
}