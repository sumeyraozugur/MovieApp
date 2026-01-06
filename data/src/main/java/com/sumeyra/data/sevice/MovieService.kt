package com.sumeyra.data.sevice

import com.sumeyra.data.model.MovieDetailResponse
import com.sumeyra.data.model.NowPlayingResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created on 5.01.2026
 * @author Sümeyra Özuğur
 */

internal interface  MovieService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(@Query("page") page: Int): NowPlayingResponseDto

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("page") page: Int): NowPlayingResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetailResponse
}