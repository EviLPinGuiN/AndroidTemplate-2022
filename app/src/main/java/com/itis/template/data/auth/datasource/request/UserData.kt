package com.itis.template.data.auth.datasource.request

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("username")
    val phone: String,
    @SerializedName("pass")
    val password: String
)