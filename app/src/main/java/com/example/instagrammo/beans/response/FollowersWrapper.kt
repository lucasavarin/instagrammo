package com.example.instagrammo.beans.response

import com.example.instagrammo.beans.rest.FollowersWrapperREST
import java.util.ArrayList

data class FollowersWrapper(
    val result: Boolean,
    val payload:List<Followers>
){
    companion object FollowersWrapper{
        fun createBusinessBean(rest: FollowersWrapperREST): com.example.instagrammo.beans.response.FollowersWrapper{
            var payload:MutableList<Followers> = ArrayList<Followers>()
            for(follower in rest.payload){
                payload.add(Followers.createBusinessBean(follower))
            }
            return FollowersWrapper(
                rest.result,
                payload
            )
        }
    }
}