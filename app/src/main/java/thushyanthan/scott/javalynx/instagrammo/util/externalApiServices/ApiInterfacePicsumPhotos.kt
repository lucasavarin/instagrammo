package thushyanthan.scott.javalynx.instagrammo.util.externalApiServices

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import thushyanthan.scott.javalynx.instagrammo.util.rest.FollowerResponse
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPicturesResponse

interface ApiInterfacePicsumPhotos {

    @GET ("v2/list")
    fun setPageAndLimit(@Query("page") page:String, @Query("limit") limit:String): Call<List<RandomPicturesResponse>>

}