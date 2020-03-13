package com.example.instagrammo.retrofit
import com.example.instagrammo.util.Session
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitController {

    private const val BASE_URL = "http://www.nbarresi.it/"

    val getClient: ApiInterface
        get() {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder().addInterceptor(object :Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder().addHeader("x-api-key", Session.token).build()
                    return chain.proceed(request)
                }
            }).addInterceptor(interceptor).build()
            val gson = GsonBuilder().create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
}