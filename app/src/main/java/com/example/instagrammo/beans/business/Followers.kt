package com.example.instagrammo.beans.business

import com.example.instagrammo.beans.response.FollowersResponseREST

data class Followers(
    val id:String,
    val name:String,
    val description:String,
    val picture:String
){
    companion object Followers{
        fun createBusinessBean(rest: FollowersResponseREST): com.example.instagrammo.beans.business.Followers {
            return Followers(
                rest.id,
                rest.name,
                rest.description,
                rest.picture
            )
        }
    }
}