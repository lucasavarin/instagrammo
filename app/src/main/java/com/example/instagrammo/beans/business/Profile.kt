package com.example.instagrammo.beans.business

import com.example.instagrammo.beans.rest.ProfileResponseREST

data class Profile(
    val profileId:String,
    val name:String,
    val description:String,
    val followersNumber:String,
    val postsNumber:String,
    val picture: String
){
    companion object Profile{
        fun createBusinessBean(rest: ProfileResponseREST): com.example.instagrammo.beans.business.Profile {
            return Profile(
                rest.profileId,
                rest.name,
                rest.description,
                rest.followersNumber,
                rest.postsNumber,
                rest.picture
            )
        }
    }
}