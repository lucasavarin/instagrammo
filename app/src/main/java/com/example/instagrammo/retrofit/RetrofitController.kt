package com.example.instagrammo.retrofit


import com.google.gson.GsonBuilder
import com.example.instagrammo.model.Session
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitController{

    private const val BASE_URL = "http://www.nbarresi.it/"

    val getClient: ApiInterface
        get() {

            val interceptor : HttpLoggingInterceptor=  HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client =  OkHttpClient.Builder()
                .connectTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L,TimeUnit.SECONDS)
                .readTimeout(30L,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor({chain ->
                    val originalRequest = chain.request()
                    val builder = originalRequest.newBuilder().header("x-api-key",
                        Session.token)
                    val finalRequest = builder.build()
                     chain.proceed(finalRequest)
                }).build()

            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}





    /*
   val base_url :String ="http://www.nbarresi.it"

    fun getIstance():ApiInterface {
        val interceptor :HttpLoggingInterceptor=  HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =  OkHttpClient.Builder().addInterceptor(interceptor).build();
        val builder = Retrofit.Builder()
            .baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
        return builder


    }


}


     */