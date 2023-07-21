package com.sitaram.gameyo.features.login

import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.navigation.NavController
import com.sitaram.gameyo.R
import com.sitaram.gameyo.features.main.User
import java.util.concurrent.Executor

class Biometric {

    // Initialize the variables
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: PromptInfo

    fun checkDeviceHasBiometric(navController: NavController, context: Context) {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                createBiometricListener(navController, context)
                createPromptInfo()
                biometricPrompt.authenticate(promptInfo)
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Toast.makeText(context, R.string.feature_not_available, Toast.LENGTH_SHORT).show()
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Toast.makeText(context, R.string.currently_unavailable, Toast.LENGTH_SHORT).show()
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Toast.makeText(context, R.string.device_not_enabled, Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createBiometricListener(navController: NavController, context: Context) {
        executor = ContextCompat.getMainExecutor(context)
//        biometricPrompt = BiometricPrompt(context, executor, object : BiometricPrompt.AuthenticationCallback() {
//                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                    super.onAuthenticationError(errorCode, errString)
//                    Toast.makeText(context, errString, Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
//                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                    super.onAuthenticationSucceeded(result)
//                    // navigate form login to main screen
//                    navController.navigate(User.Main.route) {
//                        // callback old screen
//                        popUpTo(User.Login.route) {
//                            inclusive = true // close the previous screen
//                        }
//                        Toast.makeText(context, "Login success.", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            })
    }

    // design for biomeric custom
    private fun createPromptInfo() {
        promptInfo = PromptInfo.Builder()
            .setTitle("Biometric Login for app")
            .setSubtitle("Login using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()
    }
}