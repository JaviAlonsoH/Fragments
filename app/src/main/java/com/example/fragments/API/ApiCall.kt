package com.example.fragments.API

import com.example.fragments.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiCall {
    @GET("?results=100")
    fun getUsers(): Call<UserResponse>
}