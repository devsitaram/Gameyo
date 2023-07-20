package com.sitaram.gameyo.features.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sitaram.composeapp.features.game.GameScreen
import com.sitaram.gameyo.features.forgotpassword.ForgotPasswordViewScreen
import com.sitaram.gameyo.features.home.HomeViewScreen
import com.sitaram.gameyo.features.login.LoginViewScreen
import com.sitaram.gameyo.features.message.MessageViewScreen
import com.sitaram.gameyo.features.profile.ProfileViewScreen
import com.sitaram.gameyo.features.register.SignUpViewScreen
import com.sitaram.gameyo.features.setting.SettingViewScreen

@Composable
fun GameyoAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = User.Login.route){
        // login screen
        composable(User.Login.route){
            LoginViewScreen(navController)
        }
        // register screen
        composable(User.Register.route){
            SignUpViewScreen(navController)
        }
        // password forgot screen
        composable(User.ForgotPassword.route){
            ForgotPasswordViewScreen(navController)
        }
        // main screen
        composable(User.Main.route){
            ViewOfMainPage()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewOfMainPage() {
    val navController = rememberNavController()
    val pages = listOf(
        ScreenItem.Home,
        ScreenItem.Profile,
        ScreenItem.Message,
        ScreenItem.Game,
        ScreenItem.Setting
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                pages.forEach { screen ->
                    BottomNavigationItem(modifier = Modifier.background(color = Color.White),
                        icon = {
                            Icon(
                                painterResource(screen.icon),
                                contentDescription = null,
                                // Set the desired icon color
                                tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                    Color.Black
                                } else {
                                    Color.Gray
                                }
                            )
                        },
                        label = {
                            Text(
                                screen.route,
                                style = TextStyle(fontSize = 10.sp),
                                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) {
                                    Color.Black
                                } else {
                                    Color.Gray
                                }
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // selecting the same item
                                launchSingleTop = true
                                // Restore state when selecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {innerPadding ->
        NavHost(navController, startDestination = ScreenItem.Home.route, Modifier.padding(innerPadding)) {
            composable(ScreenItem.Home.route) { HomeViewScreen() }
            composable(ScreenItem.Profile.route) { ProfileViewScreen() }
            composable(ScreenItem.Message.route) { MessageViewScreen() }
            composable(ScreenItem.Game.route) { GameScreen() }
            composable(ScreenItem.Setting.route) { SettingViewScreen() }
        }
    }
}