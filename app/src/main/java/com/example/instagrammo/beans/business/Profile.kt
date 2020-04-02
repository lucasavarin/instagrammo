package com.example.instagrammo.beans.business

import com.example.instagrammo.beans.response.ProfileResponseREST

data class Profile(
    val profileId:String,
    val name:String,
    val description:String,
    val picture:String,
    val postsNumber:String?,
    val followersNumber:String?

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