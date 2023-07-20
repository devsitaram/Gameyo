package com.sitaram.gameyo.features.forgotpassword

import android.content.Context
import android.widget.Toast
import com.sitaram.gameyo.features.database.sqlite.SQLiterDBHelper

class ForgotPasswordModel {

    // email check
    fun setEmail(email: String, context: Context): Boolean {
        val isSuccess = SQLiterDBHelper(context).getUserEmail(email)
        return if (isSuccess) {
            true
        } else {
            Toast.makeText(context, "Your mail is not register!", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    // password update
    fun setPassword(password: String, context: Context): Boolean? {
        return SQLiterDBHelper(context).updatePassword(password, context)
    }
}