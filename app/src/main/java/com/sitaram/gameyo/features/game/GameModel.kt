package com.sitaram.gameyo.features.game

import com.sitaram.gameyo.features.api.ApiCallInstance
import com.sitaram.gameyo.features.api.ApiServices
import com.sitaram.gameyo.features.api.Repository
import com.sitaram.gameyo.features.game.pojo.GameItems
import retrofit2.Call

class GameModel {

    private val repository = Repository()
    // call the api
    fun getGame(): Call<List<GameItems>>? {
        return repository.getGameList()
//        val apiService: ApiServices? = ApiCallInstance.getAPIServiceInstance()
//        return apiService?.getGames()
    }
}