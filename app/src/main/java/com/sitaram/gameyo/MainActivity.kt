package com.sitaram.gameyo

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sitaram.gameyo.features.main.GameyoAppNavHost
import com.sitaram.gameyo.ui.theme.GameyoTheme

class MainActivity : ComponentActivity() {

//    val getSharedPreferences = getSharedPreferences("selfPrefs", MODE_PRIVATE)
//    val hasIntroSlider: Boolean = getSharedPreferences.getBoolean("has_view_slider", false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //firebase notification
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            // Log and toast
            Log.d("Firebase Token", token)
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })

        // ui design
        setContent {
            GameyoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    GameyoAppNavHost(navController = navController)
                }
            }
        }
    }

//    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
//        get() = object : BiometricPrompt.AuthenticationCallback() {
//            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                super.onAuthenticationError(errorCode, errString)
//                Toast.makeText(this@MainActivity, errString, Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onAuthenticationFailed() {
//                super.onAuthenticationFailed()
//                Toast.makeText(this@MainActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                super.onAuthenticationSucceeded(result)
//                Toast.makeText(this@MainActivity, "Authenticate success", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    private fun checkBiometricSupport(): Boolean {
//        val keyGuardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//        if (!keyGuardManager.isDeviceSecure) {
//            authenticationCallback
//            Toast.makeText(this, "lock success security not enable in the security", Toast.LENGTH_SHORT).show()
//            return false
//        }
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Finger print authentication permission not enable", Toast.LENGTH_SHORT).show()
//            return false
//        }
//        return packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
//    }
//
//    private fun launchBiometric() {
//        if (checkBiometricSupport()) {
//            val promptInfo = BiometricPrompt.PromptInfo.Builder()
//                .setTitle("Biometric Login for app")
//                .setSubtitle("Login using your biometric credential")
//                .setNegativeButtonText("Cancel")
//                .build()
//            biometricPrompt?.authenticate(promptInfo)
//        }
//    }
//
//    private fun getCancellationSignal(): CancellationSignal {
//        cancellationSignal = CancellationSignal()
//        cancellationSignal?.setOnCancelListener {
//            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
//        }
//        return cancellationSignal as CancellationSignal
//    }

}

//@Composable
//fun Greeting(onClick: () -> Unit) {
//    OneTimeAPICall {
//        // Rest of your UI code
//        Column(
//            verticalArrangement = Arrangement.Center,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Button(onClick = onClick) { // Call the lambda here
//                Text(text = "Login")
//            }
//        }
//    }
//}

@Composable
fun OneTimeAPICall(apiCall: () -> Unit) {
    // Use a rememberUpdatedState to ensure the API call is made only once during the app's lifetime
    val apiCallMade by rememberUpdatedState(false)
    if (!apiCallMade) {
        // Make the API call and set the apiCallMade state to true
        apiCall()
    }
}