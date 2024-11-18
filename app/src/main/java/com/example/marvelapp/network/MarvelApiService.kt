package com.example.marvelapp.network

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

interface MarvelApiService {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int,
    ): Response<CharacterApiResponse>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharcterDetails(
        @Path("characterId") characterId: Int,
    ): Response<CharacterApiResponse>
    companion object {
        //todo: Перенести в другой файл
        private const val BASE_URL = "https://gateway.marvel.com/"
        private const val API_KEY = "80cd745bf6e1a23dadcbc6c7e4a97f2e"
        private const val PRIVATE_KEY = "9af3114f847114be1ca4473a989d2fe34eb8361f"

        fun create(): MarvelApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val timestamp = System.currentTimeMillis().toString()
                    val hash = generateHash(timestamp)

                    // Добавляем параметры в URL
                    val url = original.url.newBuilder()
                        .addQueryParameter("ts", timestamp)
                        .addQueryParameter("apikey", API_KEY)
                        .addQueryParameter("hash", hash)
                        .build()

                    val request = original.newBuilder().url(url).build()
                    chain.proceed(request)
                }
                .build()
            val moshi = Moshi.Builder().build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(MarvelApiService::class.java)
        }

        private fun generateHash(timestamp: String): String {
            val input = timestamp + PRIVATE_KEY + API_KEY
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}
