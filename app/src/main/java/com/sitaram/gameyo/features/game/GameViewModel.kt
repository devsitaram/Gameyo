package com.sitaram.gameyo.features.game

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sitaram.gameyo.features.game.GameModel
import com.sitaram.gameyo.features.game.pojo.GameItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameViewModel: ViewModel() {

    private val gameModel = GameModel()
    // To check the API call response
    fun getGames(context: Context, onListReceived: (List<GameItems>) -> Unit) {

        gameModel.getGame()?.enqueue(object : Callback<List<GameItems>> {

            override fun onResponse(call: Call<List<GameItems>>, response: Response<List<GameItems>>) {
                if (response.isSuccessful) {
                    // add the list
                    onListReceived.invoke(response.body() ?: emptyList())
                    Toast.makeText(context, "Api call Success.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Api call unsuccessful.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<GameItems>>, throwable: Throwable) {
                throwable.printStackTrace()
                Log.d("onFailure", "API call failed: $throwable")
            }

        })
    }
}
