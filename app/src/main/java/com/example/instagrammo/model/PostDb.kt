package com.example.instagrammo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostDb(val profileId :String,
                  val postId:String,
 val title :String,
 val uploadTime :String?)