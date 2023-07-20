package com.sitaram.composeapp.features.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sitaram.gameyo.features.game.GameViewModel
import com.sitaram.gameyo.features.game.pojo.GameItems


//private const val TAG = "GameScreen"
val gameViewModel = GameViewModel()

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GameScreen() {

    val context = LocalContext.current

    val gameList = remember {
        mutableStateOf(ArrayList<GameItems>())
    }

    LaunchedEffect(true) {
        gameViewModel.getGames(context) {
            gameList.value = it as ArrayList<GameItems>
        }
    }

    // call the composable function
    getListOfGames(gameList, context)
}

@SuppressLint("ComposableNaming")
@Composable
fun getListOfGames(gameList: MutableState<ArrayList<GameItems>>, context: Context) {

//    val onClickAction: (Context) -> Unit = { context ->
//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.data = Uri.parse("https://www.youtube.com/**chanel**")
//        intent.setPackage("com.google.android.youtube")
//        context.startActivity(intent)
//    }

    LazyColumn {
        items(gameList.value.size) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(text = gameList.value[it].id.toString())
                val title = gameList.value[it].title ?: "title"
                Text(text = title)
                Image(
                    painter = rememberAsyncImagePainter(gameList.value[it].thumbnail ?: "image"),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
                Text(text = gameList.value[it].shortDescription ?: "descriptions")
                Text(text = gameList.value[it].releaseDate ?: "releaseDate")
                Text(text = gameList.value[it].freetogameProfileUrl ?: "freetogameProfileUrl")
                Text(text = gameList.value[it].genre ?: "genre")
                Text(text = gameList.value[it].publisher ?: "publisher")
                Text(text = gameList.value[it].developer ?: "developer")
                Text(text = gameList.value[it].platform ?: "platform")

                Row(Modifier.fillMaxWidth()) {
                    // play store button
                    Button(onClick = {
                        val webIntent = Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/search?q=$title=apps"))
                        context.startActivity(webIntent)

//                        val intent = Intent(Intent.ACTION_VIEW)
//                        intent.data = Uri.parse("https://www.playstore.com/$title")
//                        intent.setPackage("com.google.android.playstore")
//                        context.startActivity(intent)
                    }) {
                        Text(text = "Play Store")
                    }

                    // you tube button
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("https://www.youtube.com/search?q=$title=games")
                        intent.setPackage("com.google.android.youtube")
                        context.startActivity(intent) }) {
                        Text(text = "YouTube")
                    }
                }
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)) // divider
        }
    }
}
