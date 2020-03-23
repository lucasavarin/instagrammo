package com.mst.instagrammo.api

import com.mst.instagrammo.utilities.Session
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    const val BASE_URL = "http://www.nbarresi.it/"

    val getClient: ApiInterface
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor { chain ->
                    val firstRequest = chain.request()
                    val builder = firstRequest.newBuilder().header("x-api-key", Session.authToken)
                    val finalRequest = builder.build()
                    chain.proceed(finalRequest)
                }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}