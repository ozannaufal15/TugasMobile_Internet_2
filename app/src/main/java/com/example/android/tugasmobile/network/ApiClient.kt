package com.example.android.tugasmobile.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NyTimesApiService {
    @GET("viewed/1.json")
    suspend fun getPopularArticles(@Query("api-key") key: String): ArticleResponse
}

object NyTimesApi {
    val retrofitService: NyTimesApiService by lazy {
        retrofit.create(NyTimesApiService::class.java)
    }
}