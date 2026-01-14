package com.sumeyra.data.intercepter

import com.sumeyra.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {

   private val token = BuildConfig.TMDB_BEARER

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .apply {
                addHeader("Authorization", "Bearer $token")
            }
            .build()
        return chain.proceed(request)
    }
}
