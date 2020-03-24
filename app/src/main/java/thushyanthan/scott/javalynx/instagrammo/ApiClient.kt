package thushyanthan.scott.javalynx.instagrammo

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

import okhttp3.internal.platform.Platform.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import thushyanthan.scott.javalynx.instagrammo.util.sharedPrefs.prefs
import kotlin.coroutines.coroutineContext

object ApiClient {
    val base_url: String = "http://www.nbarresi.it/"
    val getClient: ApiInterface
       get() {
            val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()

           httpClient.addInterceptor(object :Interceptor{
               override fun intercept(chain: Interceptor.Chain): Response {
                   val req = chain.request().newBuilder().addHeader("x-api-key", prefs.token).build()
                   return chain.proceed(req)
               }
           })

            logging.level=HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

            var gson = GsonBuilder().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()





            return retrofit.create(ApiInterface::class.java)
        }

}