package com.sitaram.gameyo.features.api

import com.sitaram.composeapp.features.game.pojo.GameItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    // local server data
    @GET("games")
    fun getGames(): Call<List<GameItems>>
}