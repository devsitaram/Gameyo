package com.sitaram.gameyo.features.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    // create the object of Login Model class
    private val loginModel = LoginModel()

    fun loginDetails(userName: String, userPassword: String, context: Context): Boolean {
        return if (userName.isNotEmpty() && userPassword.isNotEmpty()){
            getUser(userName, userPassword, context)
        } else {
            Toast.makeText(context, "The fields is empty!", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun getUser(userName: String, userPassword: String, context: Context): Boolean {
        val isSuccess = loginModel.getLoginUser(userName, userPassword, context)
        return if (isSuccess) {
            Toast.makeText(context, "Login Successful.", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}