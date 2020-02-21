package thushyanthan.scott.javalynx.instagrammo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import thushyanthan.scott.javalynx.instagrammo.R
import java.util.ArrayList

class HomeFragment: Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home,container,false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addItems()
        homeFollowerListLayout.layoutManager = LinearLayoutManager(context)
        homeFollowerListLayout .adapter = HomeAdapter(provaArray,context!!)
    }

    val provaArray:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun addItems(){
        provaArray.add("1")
        provaArray.add("2")
        provaArray.add("3")
        provaArray.add("4")
        provaArray.add("5")
        provaArray.add("6")
        provaArray.add("7")
        provaArray.add("8")
        provaArray.add("9")
        provaArray.add("10")
        provaArray.add("1")
        provaArray.add("2")
        provaArray.add("3")
        provaArray.add("4")
        provaArray.add("5")
        provaArray.add("6")
        provaArray.add("7")
        provaArray.add("8")
        provaArray.add("9")
        provaArray.add("10")

    }







}