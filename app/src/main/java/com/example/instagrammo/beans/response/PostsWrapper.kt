package com.example.instagrammo.beans.response

import android.service.voice.AlwaysOnHotwordDetector
import com.example.instagrammo.beans.rest.PostsWrapperResponseREST
import java.util.ArrayList

data class PostsWrapper(
    val result:Boolean,
    val payload: List<Post>
){
    companion object PostsWrapper{
        fun createBusinessBean(rest: PostsWrapperResponseREST): com.example.instagrammo.beans.response.PostsWrapper{
            var payload:MutableList<Post> = ArrayList<Post>()
            for(post in rest.payload){
                payload.add(Post.createBusinessBean(post))
            }
            return PostsWrapper(
                rest.result,
                payload
            )
        }
    }
}