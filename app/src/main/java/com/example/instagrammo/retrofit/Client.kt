package com.example.instagrammo.retrofit
import com.example.instagrammo.Session
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Client {
    const val BASE_URL: String = "http://www.nbarresi.it/"

    val getClient: ApiInterface
    get() {
        val interceptor= HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
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