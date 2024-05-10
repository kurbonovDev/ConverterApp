package com.example.converter.retrofit

import com.example.converter.retrofit.data.MainApi
import com.example.converter.retrofit.data.models.ExchangerItem
import com.example.converter.retrofit.data.models.NbtItem
import com.example.converter.utils.baseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {


    private fun createClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .client(createClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val currencyRateApiServices = retrofit.create(MainApi::class.java)

    suspend fun getNbtRates(): List<NbtItem> = currencyRateApiServices.getNbtRates()

    suspend fun getBanksRates(): MutableList<ExchangerItem> =
        currencyRateApiServices.getBanksRates()
}