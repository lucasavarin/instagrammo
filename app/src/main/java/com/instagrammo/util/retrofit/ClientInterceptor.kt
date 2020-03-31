package com.instagrammo.util.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ClientInterceptor {

    val URL = "http://www.nbarresi.it"

    val getUser : APiInterface
        get() {
            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =  OkHttpClient().newBuilder().addInterceptor(logger).addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                requestBuilder.addHeader("x-api-key",
                    Session.token
                )
                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val builder  = Retrofit.Builder()
            .baseUrl(URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(APiInterface::class.java)
    }


    val getLogin : APiInterface
        get() {
            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val client  = OkHttpClient().newBuilder().addInterceptor(logger)

            val builder  = Retrofit.Builder()
                .baseUrl(URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return builder.create(APiInterface::class.java)
        }


}