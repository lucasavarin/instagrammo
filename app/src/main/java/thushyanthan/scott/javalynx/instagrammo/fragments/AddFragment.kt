package thushyanthan.scott.javalynx.instagrammo.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_random_pics_list_item.*
import kotlinx.android.synthetic.main.fragment_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thushyanthan.scott.javalynx.instagrammo.ApiClientPicsumPhotos
import thushyanthan.scott.javalynx.instagrammo.R
import thushyanthan.scott.javalynx.instagrammo.adapter.AddAdapter
import thushyanthan.scott.javalynx.instagrammo.fragments.secondaryFragments.AddFragmentSecond
import thushyanthan.scott.javalynx.instagrammo.util.RecyclerViewItemDecorator
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPictures
import thushyanthan.scott.javalynx.instagrammo.util.rest.RandomPicturesResponse
import java.util.ArrayList

class AddFragment : Fragment() {
    var randomPics: List<RandomPictures> = ArrayList()
    private lateinit var linearLayoutManagerRandomPics: GridLayoutManager
    var addAdapter : AddAdapter?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_add, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getRandomPictures()
        linearLayoutManagerRandomPics = GridLayoutManager(activity, 3)
        randomPictures.layoutManager = linearLayoutManagerRandomPics
        randomPictures.addItemDecoration(RecyclerViewItemDecorator(4))


    }

    fun getRandomPictures() {
        val randomPicCall = ApiClientPicsumPhotos.getClient.setPageAndLimit("1", "30")
        randomPicCall.enqueue(object : Callback<List<RandomPicturesResponse>> {
            override fun onFailure(call: Call<List<RandomPicturesResponse>>, t: Throwable) {
                Toast.makeText(activity, "Error getRandomPictures1", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<RandomPicturesResponse>>,
                response: Response<List<RandomPicturesResponse>>
            ) {
                if (response.isSuccessful) {
                    val resultBody = response.body()!!
                    randomPics = resultBody.map { randomPicResponse ->
                        val indexedMap =
                            randomPicResponse.download_url.split("/").mapIndexed { index, s ->
                                val arrayDownloadPath = randomPicResponse.download_url.split("/")
                                if (index == arrayDownloadPath.size - 1 || index == arrayDownloadPath.size - 2) {
                                    return@mapIndexed "400"
                                }
                                return@mapIndexed s

                            }

                        val randomPic = RandomPictures(randomPicResponse.download_url, indexedMap.joinToString ( separator = "/" ))
                        return@map randomPic
                    }
                    addAdapter = AddAdapter(randomPics)
                    addAdapter?.adapterSetOnPicClickedListener{
                        makeTransaction(it)
                    }
                    randomPictures.adapter = addAdapter
                    randomPictures.adapter?.notifyDataSetChanged()
                } else {
                    Toast.makeText(activity, "Error getRandomPictures2", Toast.LENGTH_SHORT).show()
                }

            }
        })




    }
    fun makeTransaction(randomPic:RandomPictures){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, AddFragmentSecond.makeInstance(randomPic))?.commit()
    }

}