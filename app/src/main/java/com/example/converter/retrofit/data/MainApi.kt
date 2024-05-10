package com.example.converter.retrofit.data

import com.example.converter.retrofit.data.models.ExchangerItem
import com.example.converter.retrofit.data.models.NbtItem
import retrofit2.http.GET

interface MainApi {

    @GET("nbt_rates")
    suspend fun getNbtRates(): List<NbtItem>

    @GET("npcr_bank_rates")
    suspend fun getBanksRates(): MutableList<ExchangerItem>
}