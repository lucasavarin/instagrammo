package com.example.instagrammo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagrammo.R
import com.example.instagrammo.model.SaveDataBean
import com.example.instagrammo.model.SaveUserDataResponseBean
import com.example.instagrammo.model.Session
import com.example.instagrammo.picassotransformation.CircleTrasformation
import com.example.instagrammo.retrofit.RetrofitController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_modifica_profilo_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModificaProfiloFragment private constructor(
    val id: String,
    private val nome: String,
    private val desc: String,
    private val picture: String
) : Fragment() {

    companion object {


        fun getIstance(
            id: String,
            nome: String,
            desc: String,
            picture: String
        ): ModificaProfiloFragment {
            return ModificaProfiloFragment(
                id,
                nome,
                desc,
                picture
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_modifica_profilo_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPhoto()
        (name_input as TextView).text = this.nome
        (desc_input as TextView).text = this.desc


        headercustom.setOnBackClickListener {
            fragmentManager!!.beginTransaction().remove(this).commit()
        }

        val retrofit = RetrofitController.getClient
        savedata.setOnClickListener {
            if (noNewDataFound()) {
                Toast.makeText(view.context, "Campi vuoti", Toast.LENGTH_SHORT).show()
            } else {
                val imageUrl =
                    if (modTxv.text != null && modTxv.text.toString() != "") "https://i.picsum.photos/id/${modTxv.text.toString()}/400/400.jpg" else picture
                val nomeDaSalvare =
                    if (name_input.text != null && name_input.text.toString() != "") name_input.text.toString() else this.nome
                val descDaSalvare =
                    if (desc_input.text != null && desc_input.text.toString() != "") desc_input.text.toString() else this.desc
                retrofit.saveUserData(
                    SaveDataBean(
                        id,
                        nomeDaSalvare, descDaSalvare, imageUrl
                    )
                ).enqueue(object : Callback<SaveUserDataResponseBean> {
                    override fun onFailure(call: Call<SaveUserDataResponseBean>, t: Throwable) {
                        Toast.makeText(view.context, "Errore", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<SaveUserDataResponseBean>,
                        response: Response<SaveUserDataResponseBean>
                    ) {

                        Toast.makeText(view.context, "Salvataggio riuscito", Toast.LENGTH_SHORT)
                            .show()
                        fragmentManager!!.beginTransaction().remove(ProfiloFragment.newInstance())
                            .replace(R.id.frame,ProfiloFragment.newInstance()).commit()
                    }
                })
            }
        }

        fab.setOnClickListener {
            changePhoto(modTxv.text.toString())
        }
    }

    private fun changePhoto(id: String) {
        if (id != "")
            Picasso.get().load("https://i.picsum.photos/id/$id/400/400.jpg").resize(275, 275)
                .centerInside().transform(CircleTrasformation()).into(imageProf)
    }

    private fun loadPhoto() {
        Picasso.get().load(this.picture).resize(275, 275)
            .centerInside().transform(CircleTrasformation()).into(imageProf)
    }

    private fun noNewDataFound(): Boolean =
        modTxv.text.toString() == "" && name_input.text.toString() == "" && desc_input.text.toString() == ""
}