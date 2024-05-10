package com.example.converter.retrofit.data.models

import com.google.gson.annotations.SerializedName

data class ExchangerItem(
    @SerializedName("bank_name")
    val bankName: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("Currency")
    val currency: List<Currency>
)

data class Currency(
    @SerializedName("name")
    val name: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("sell_value")
    val sellValue: Float,
    @SerializedName("buy_value")
    val buyValue: Float,
    @SerializedName("full_name")
    val fullName: String,

)