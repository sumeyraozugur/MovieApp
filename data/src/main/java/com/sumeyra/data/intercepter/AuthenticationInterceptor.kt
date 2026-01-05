package com.sumeyra.data.intercepter

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {

   private val token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0NDI4MTMyYzZkZDU2ZjYzZGMyNjAxNDdkZmI2Y2I5NyIsIm5iZiI6MTc1NTM2ODYzNy40Miwic3ViIjoiNjhhMGNjYmRhMmE5NjExYWZiNjMyMGRkIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.8U50YP-ulNhQFjGKnMt-rvo0c4h9W2KkzwEpZJq22tQ"


    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .apply {
                addHeader("Authorization", "Bearer $token")
            }
            .build()
        return chain.proceed(request)
    }
}
