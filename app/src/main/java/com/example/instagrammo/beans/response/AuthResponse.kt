package com.example.instagrammo.beans.response
import com.example.instagrammo.beans.rest.AuthResponseREST

class AuthResponse(
    val result: Boolean,
    val authToken: String,
    val profileId: Int
){
    companion object AuthResponse{
        fun createBusinessBean(rest: AuthResponseREST): com.example.instagrammo.beans.response.AuthResponse{
            return AuthResponse(
                rest.result,
                rest.authToken,
                rest.profileId
            )
        }
    }
}