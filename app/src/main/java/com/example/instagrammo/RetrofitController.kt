package com.example.instagrammo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitController{

   val base_url :String ="http://www.nbarresi.it"

    fun getIstance():ApiInterface {
        val interceptor :HttpLoggingInterceptor=  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val  client =  OkHttpClient.Builder().addInterceptor(interceptor).build();
        val builder = Retrofit.Builder()
            .baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
        return builder


    }


}