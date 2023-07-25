package com.sitaram.gameyo.features.game

import android.content.Context
import com.sitaram.gameyo.features.game.pojo.GameItems
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GameRepository {
    private val gameViewModel = GameViewModel()
    private var gameList: ArrayList<GameItems>? = null

    suspend fun getGameList(context: Context): ArrayList<GameItems> {
        if (gameList == null) {
            // Make the API call only if the data is not already cached
            gameList = suspendCoroutine { continuation ->
                gameViewModel.getGames(context) { gameItemsList ->
                    continuation.resume(gameItemsList as ArrayList<GameItems>)
                }
            }
        }
        return gameList ?: ArrayList()
    }
}