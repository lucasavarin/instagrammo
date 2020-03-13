package com.example.instagrammo.beans.response

import com.example.instagrammo.beans.rest.AuthResponseREST
import com.example.instagrammo.beans.rest.FollowersResponseREST

data class Followers(
    val id:String,
    val name:String,
    val description:String,
    val picture:String
){
    companion object Followers{
        fun createBusinessBean(rest: FollowersResponseREST): com.example.instagrammo.beans.response.Followers{
            return Followers(
                rest.id,
                rest.name,
                rest.description,
                rest.picture
            )
        }
    }
}