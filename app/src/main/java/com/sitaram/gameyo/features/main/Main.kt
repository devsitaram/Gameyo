package com.sitaram.gameyo.features.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sitaram.gameyo.features.login.LoginViewScreen
import com.sitaram.gameyo.features.register.SignUpViewScreen

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

        }
        // main screen
        composable(User.Main.route){

        }
    }
}