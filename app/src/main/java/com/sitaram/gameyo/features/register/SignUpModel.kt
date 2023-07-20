package com.sitaram.gameyo.features.register

import android.content.Context
import com.sitaram.gameyo.features.database.sqlite.SQLiterDBHelper

class SignUpModel {

    // register function
    fun registerDetails(email: String, name: String, password: String, context: Context): Boolean? {
        val databaseHelper = SQLiterDBHelper(context)
        // call the register button click method
        return databaseHelper.registerUser(email, name, password)

    }
}