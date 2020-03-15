package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import kotlinx.android.synthetic.main.profilo_fragment_layout.*

class ProfiloFragment : Fragment(){


    companion object {

        fun newInstance(): ProfiloFragment {
            val fragment = ProfiloFragment()

            return fragment
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profilo_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonProfilo.setOnClickListener { v->
            val f =
                ModificaProfiloFragment.getIstance(
                    "1",
                    "cristian",
                    "ciao",
                    ""
                )
            fragmentManager!!.beginTransaction().add(R.id.frame,f,"TAG")
               .commit()

        }

    }
}