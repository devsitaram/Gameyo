package com.sitaram.gameyo.features.register

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.sitaram.gameyo.features.util.Validator

class SignUpViewModel: ViewModel() {

    private val signUpModel = SignUpModel()
    private val validator = Validator()

    // register function
    fun registerDetail(email: String, name: String, password: String, context: Context): Boolean {

        // initialize the variable
        val isValidEmail = validator.emailValidation(email)
        val isValidName = validator.nameValidation(name)

        return if (!isValidEmail || !isValidName) {
            Toast.makeText(context, "Enter the valid details!", Toast.LENGTH_SHORT).show()
            false
        } else {
            // call the register button click method
            return register(email, name, password, context)
        }
    }

    /**
     * @param email -> User's email
     * @param name -> User's name
     * @param password -> User's password
     */
    private fun register(email: String, name: String, password: String, context: Context): Boolean {
        val isRegisterSuccess = signUpModel.registerDetails(email, name, password, context)
        return if (isRegisterSuccess == true) {
            Toast.makeText(context, "Register Success.", Toast.LENGTH_SHORT).show()
            true
        } else {
            Toast.makeText(context, "Please enter the valid data", Toast.LENGTH_SHORT).show()
            false
        }
    }
}