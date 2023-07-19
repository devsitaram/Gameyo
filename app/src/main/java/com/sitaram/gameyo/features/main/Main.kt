package com.sitaram.gameyo.features.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sitaram.gameyo.features.login.LoginViewScreen

@Composable
fun GameyoAppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = User.Login.route){
        // login screen
        composable(User.Login.route){
            LoginViewScreen()
        }
        // register screen
        composable(User.Register.route){

        }
        // password forgot screen
        composable(User.ForgotPassword.route){

        }
        // main screen
        composable(User.Main.route){

        }
    }
}