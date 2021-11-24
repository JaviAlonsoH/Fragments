package com.example.fragments.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val users: List<User>
)