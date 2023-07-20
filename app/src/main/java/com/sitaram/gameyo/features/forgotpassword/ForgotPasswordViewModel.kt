package com.sitaram.gameyo.features.forgotpassword

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sitaram.gameyo.features.util.Validator

class ForgotPasswordViewModel: ViewModel() {

    private val forgotPasswordModel = ForgotPasswordModel()
    private val validator = Validator()

    // test field empty check
    fun getUserEmail(email: String, context: Context): Boolean {
        return if (email.isEmpty()){
            Toast.makeText(context, "Email is empty!", Toast.LENGTH_SHORT).show()
            false
        } else {
            return userEmail(email, context)
        }
    }

    // email validation check
    private fun userEmail(email: String, context: Context): Boolean {
        val isBoolean = validator.emailValidation(email)
        return if (!isBoolean){
            Toast.makeText(context, "Invalid your email!", Toast.LENGTH_SHORT).show()
            false
        } else {
            return forgotPasswordModel.setEmail(email, context)
        }
    }

    // test field empty check
    fun getUserPassword(password: String, context: Context): Boolean {
        return if (password.isEmpty()) {
            Toast.makeText(context, "Password is empty!", Toast.LENGTH_SHORT).show()
            false
        } else {
            return userPassword(password, context)
        }
    }

    // password validation check
    private fun userPassword(password: String, context: Context): Boolean {
        val isChange = forgotPasswordModel.setPassword(password, context)
        return if (isChange == true){
            Toast.makeText(context, "Successfully change.", Toast.LENGTH_SHORT).show()
            true
        } else {
//            Toast.makeText(context, "Invalid your email!", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}