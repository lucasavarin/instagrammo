package thushyanthan.scott.javalynx.instagrammo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
        @POST(value = "auth.php")
        fun doAuth(
            @Body auth: DataRequest
        ):Call<AuthResponse>

        @GET("posts.php")
        fun requestPosts(): Call<PostsResponse>
}