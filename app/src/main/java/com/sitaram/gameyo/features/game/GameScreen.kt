package com.sitaram.gameyo.features.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sitaram.gameyo.R
import com.sitaram.gameyo.features.game.pojo.GameItems

//private const val TAG = "GameScreen"
val gameViewModel = GameViewModel()

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GameScreen() {
    val context = LocalContext.current
    val gameItemsList = remember { mutableStateOf(ArrayList<GameItems>()) }
    Surface(Modifier.fillMaxWidth()) {
        // get data from gameModelView class
        LaunchedEffect(true) {
            gameViewModel.getGames(context) {
                gameItemsList.value = it as ArrayList<GameItems>
            }
        }

        // call the composable function
        GetListOfGames(gameItemsList, context)
    }
//    LaunchedEffect(true) {
//        // Call the API to get the game data
//        val newData = Game(context).getGameFromApi()
//        gameItemsList.value.addAll(newData)
//        Log.e("Game is API Call: ", "$gameItemsList")
//    }
//
//    Surface(Modifier.fillMaxWidth()) {
//        if (gameItemsList.value.isNotEmpty()) {
//            // call the composable function
//            GetListOfGames(gameItemsList, context)
//        }
//    }
}

@Composable
fun GetListOfGames(gameItemsList: MutableState<ArrayList<GameItems>>, context: Context) {
    val gameList: MutableState<ArrayList<GameItems>> = gameItemsList
    LazyColumn {
        Log.d("Game is success: ", "$gameItemsList")
        items(gameList.value.size) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                Text(text = gameList.value[it].id.toString())
                val title = gameList.value[it].title ?: "title"
                Text(text = title)
                Image(
                    painter = rememberAsyncImagePainter(gameList.value[it].thumbnail ?: "image"),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clickable {
                            val webIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://google.com/search?q=$title/game")
                            )
                            context.startActivity(webIntent)
                        }
                )
                Text(text = gameList.value[it].shortDescription ?: "descriptions")
                Text(text = gameList.value[it].releaseDate ?: "releaseDate")
                Text(text = gameList.value[it].freetogameProfileUrl ?: "freetogameProfileUrl")
                Text(text = gameList.value[it].genre ?: "genre")
                Text(text = gameList.value[it].publisher ?: "publisher")
                Text(text = gameList.value[it].developer ?: "developer")
                Text(text = gameList.value[it].platform ?: "platform")

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // play store button
                    GooglePlaySearchButton(title, context)
                    // you tube button
                    YouTubeSearchButton(title, context)
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) // divider
        }
    }
}

//@SuppressLint("MutableCollectionMutableState")
//@Composable
//fun GameScreen() {
//    val context = LocalContext.current
//    val gameItemsList = remember { mutableStateOf(ArrayList<GameItems>()) }
//
//    Surface(Modifier.fillMaxWidth()) {
//        // get data from gameModelView class
//        LaunchedEffect(true) {
//            gameViewModel.getGames(context) {
//                gameItemsList.value = it as ArrayList<GameItems>
//            }
//        }
//
//        // call the composable function
//        getListOfGames(gameItemsList, context)
//    }
//}
//@SuppressLint("ComposableNaming")
//@Composable
//fun getListOfGames(gameItemsList: MutableState<ArrayList<GameItems>>, context: Context) {
//    val gameList: MutableState<ArrayList<GameItems>> = gameItemsList
//    LazyColumn {
//        items(gameList.value.size) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(10.dp)
//            ) {
//                Text(text = gameList.value[it].id.toString())
//                val title = gameList.value[it].title ?: "title"
//                Text(text = title)
//                Image(
//                    painter = rememberAsyncImagePainter(gameList.value[it].thumbnail ?: "image"),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(240.dp)
//                        .clickable {
//                            val webIntent = Intent(
//                                Intent.ACTION_VIEW,
//                                Uri.parse("https://google.com/search?q=$title/game")
//                            )
//                            context.startActivity(webIntent)
//                        }
//                )
//                Text(text = gameList.value[it].shortDescription ?: "descriptions")
//                Text(text = gameList.value[it].releaseDate ?: "releaseDate")
//                Text(text = gameList.value[it].freetogameProfileUrl ?: "freetogameProfileUrl")
//                Text(text = gameList.value[it].genre ?: "genre")
//                Text(text = gameList.value[it].publisher ?: "publisher")
//                Text(text = gameList.value[it].developer ?: "developer")
//                Text(text = gameList.value[it].platform ?: "platform")
//
//                Row(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(top = 10.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    // play store button
//                    GooglePlaySearchButton(title, context)
//                    // you tube button
//                    YouTubeSearchButton(title, context)
//                }
//            }
//            Divider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 20.dp)
//            ) // divider
//        }
//    }
//}

//@Preview
@Composable
fun GooglePlaySearchButton(title: String, context: Context) {
    IconButton(
        onClick = {
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/search?q=$title/app")
            )
            context.startActivity(webIntent)
        },
        modifier = Modifier.background(color = Color.Black).wrapContentWidth(),
    ) {
        Row(
            modifier = Modifier.padding(start = 15.dp, end = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_google_play),
                contentDescription = "Google Play",
                modifier = Modifier.size(30.dp)
            )
            Column(Modifier.padding(5.dp)) {
                Text(
                    text = "GET IT ON",
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    color = Color.White
                )
                Text(
                    text = "Google Play",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun YouTubeSearchButton(title: String, context: Context) {
    IconButton(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.youtube.com/search?q=$title/game")
            intent.setPackage("com.google.android.youtube")
            context.startActivity(intent)
        },
        modifier = Modifier.background(color = Color.Red).wrapContentWidth(),
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_youtube),
                contentDescription = "YouTube",
                modifier = Modifier
                    .size(40.dp)
                    .background(color = Color.Red)
            )
            Column(Modifier.padding(5.dp)) {
                Text(
                    text = "Find us on",
                    style = TextStyle(
                        fontSize = 10.sp
                    ),
                    color = Color.White
                )
                Text(
                    text = "YouTube",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White
                )
            }
        }
    }
}