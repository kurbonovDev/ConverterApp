package com.example.converter.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class NbtItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nominal")
    val nominal: Float,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("value")
    val value: Float
)
