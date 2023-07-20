package com.sitaram.gameyo.features.api

import com.sitaram.gameyo.features.game.pojo.GameItems
import retrofit2.Call
import retrofit2.Retrofit

class Domain {

    // call the api
    fun getGame(): Call<List<GameItems>> {
        val retrofit: Retrofit? = ApiCallInstance.getRetrofitInstance()
        return retrofit?.create(ApiServices::class.java)?.getGames()
            ?: throw IllegalStateException("Retrofit instance or service is null.")
    }
}