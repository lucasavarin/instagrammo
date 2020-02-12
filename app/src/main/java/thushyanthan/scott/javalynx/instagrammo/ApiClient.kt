package thushyanthan.scott.javalynx.instagrammo

import com.google.gson.GsonBuilder
import okhttp3.internal.platform.Platform.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val base_url:String = "www.nbarresi.it/"
    fun getClient():ApiInterface{
        var gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(ApiInterface::class.java)
    }

}