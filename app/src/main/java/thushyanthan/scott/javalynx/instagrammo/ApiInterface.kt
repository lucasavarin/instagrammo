package thushyanthan.scott.javalynx.instagrammo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.*
import thushyanthan.scott.javalynx.instagrammo.util.rest.*

interface ApiInterface {
        @POST("auth.php")
        fun doAuth(
            @Body auth: DataRequest
        ):Call<AuthResponse>

        @POST("followers.php")
        fun doResonseO(
        ):Call<HomeRecyclerViewOrizzontale>

        @GET("posts.php")
        fun requestPosts(): Call<PostsResponse>

        @GET("followers.php/{profiloUtente}")
        fun requestFollowers(@Path("profiloUtente") profiloUtente:String ): Call<FollowerResponse>

        @PUT("profiles.php")
        fun saveProfileEdits(@Body bodyProfilo: EditProfileBody): Call<OnlyResultResponse>

        @GET("profiles.php/{profiloUtente}")
        fun getSingleProfile(@Path("profiloUtente") profiloUtente: String): Call<ProfileResponse>

        @POST("posts.php")
        fun addPostsToProfile(@Body bodyPicToBeAdded: AddPictureRequest?): Call<OnlyResultResponse>

        @GET("posts_number.php")
        fun getNewPublishenPostsNumber():Call<NewPublishedPostsNumberResponse>

}