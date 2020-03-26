package thushyanthan.scott.javalynx.instagrammo.activities

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.loader.content.Loader
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.ApiClient
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.fragments.*
import thushyanthan.scott.javalynx.instagrammo.services.NewPostsForegroundService
import thushyanthan.scott.javalynx.instagrammo.util.rest.NewPublishedPostsNumberResponse

class MainActivity: AppCompatActivity(){
    lateinit var mainHandler: Handler


    private val something = object : Runnable {
        override fun run() {
            getNewPosts()
            mainHandler.postDelayed(this,5000)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        isGooglePlayServicesAvailable( this)
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.setOnNavigationItemSelectedListener(navListener)

            //I added this if statement to keep the selected fragment when rotating the device
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    HomeFragment()).commit()
            }
            mainHandler = Handler(Looper.getMainLooper())

            FirebaseApp.initializeApp(applicationContext)

            //GET FIREBASE TOKEN WHENEVER YOU WANT
            /*FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    val token = task.result?.token
                    val msg = getString(R.string.msg_token_fmt, token)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                })*/


    }

    fun isGooglePlayServicesAvailable(activity : Activity): Boolean{
        val googleApiAvailability : GoogleApiAvailability = GoogleApiAvailability.getInstance()
        val status = googleApiAvailability.isGooglePlayServicesAvailable(activity)
        if (status != ConnectionResult.SUCCESS){
            if (googleApiAvailability.isUserResolvableError(status)){
                googleApiAvailability.getErrorDialog(activity,status,2404).show()
            }
            googleApiAvailability.makeGooglePlayServicesAvailable(activity)
            return false
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(something)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(something)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = HomeFragment()
            R.id.navigation_search -> selectedFragment = SearchFragment()
            R.id.navigation_add -> selectedFragment = AddFragment()
            R.id.navigation_favorite -> selectedFragment = FavoriteFragment()
            R.id.navigation_profile -> selectedFragment = ProfileFragment()
        }

        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container
                , selectedFragment).commit()
        }

        true
    }

    fun getNewPosts(){
       val call = ApiClient.getClient.getNewPublishedPostsNumber()
        call.enqueue(object : Callback<NewPublishedPostsNumberResponse>{
            override fun onFailure(call: Call<NewPublishedPostsNumberResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error getNewPosts1", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<NewPublishedPostsNumberResponse>,
                response: Response<NewPublishedPostsNumberResponse>
            ) {
                if (response.isSuccessful){
                    val resultBody = response.body()!!
                    if (resultBody.result){
                        var count = 0
                        if (homePostsListLayout.adapter != null){
                            count = homePostsListLayout!!.adapter!!.itemCount
                            NewPostsForegroundService.startService(applicationContext,(resultBody.payload.toInt() - count).toString())
                            val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

                            if (resultBody.payload.toInt() - count > 0)
                            bottomNav.getOrCreateBadge(R.id.navigation_home).number = resultBody.payload.toInt() - count


                        }

                    }
                }else{
                    Toast.makeText(applicationContext, "Error getNewPosts2", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



}
