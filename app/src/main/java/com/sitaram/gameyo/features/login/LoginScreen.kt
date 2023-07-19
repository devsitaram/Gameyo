package com.sitaram.gameyo.features.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sitaram.gameyo.R
import com.sitaram.gameyo.features.main.User
import com.sitaram.gameyo.features.util.CheckboxComponent
import com.sitaram.gameyo.features.util.HeadingTextComponent
import com.sitaram.gameyo.features.util.InputTextField
import com.sitaram.gameyo.features.util.NormalButton
import com.sitaram.gameyo.features.util.NormalTextComponent
import com.sitaram.gameyo.features.util.PasswordTextField

@Preview
@Composable
fun LoginViewScreen() {

    val navController = rememberNavController()
    Surface(Modifier.fillMaxSize()) {
        val context = LocalContext.current
//        val loginViewModel = LoginViewModel()

        var name by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        // Login button click handler
        val onLoginClick: () -> Unit = {
//            val isValidLogin = loginViewModel.loginDetails(name, password, context)
            val isValidLogin = false
            if (isValidLogin) {
                // Navigate to the home screen
                navController.navigate(User.Main.route) {
                    // callback old screen
                    popUpTo(User.Login.route) {
                        inclusive = true // close the previous screen
                    }
                }
                Toast.makeText(context, "Login Successful.", Toast.LENGTH_SHORT).show()
            }
        }

        // sign screen page
        Surface(modifier = Modifier
            .fillMaxSize()) {
            // child layout file
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
            ) {
                NormalTextComponent(
                    text = stringResource(id = R.string.hey),
                    color = colorResource(id = R.color.softBlack)
                ) // text

                HeadingTextComponent(
                    value = stringResource(id = R.string.login_your_details),
                    color = colorResource(id = R.color.black)
                )

//                Spacer(modifier = Modifier.height(50.dp)) // marginTop/space

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                        .background(Color.White)) {
                    // username
                    InputTextField(
                        name,
                        onValueChange = { name = it },
                        label = stringResource(id = R.string.userName),
                        painterResource(R.drawable.ic_person),
                    )

                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    // password
                    PasswordTextField(
                        password,
                        painterResource = painterResource(id = R.drawable.ic_lock),
                        onValueChange = { password = it },
                        label = stringResource(id = R.string.userPassword)
                    )

                    // checkbox
                    CheckboxComponent()

                    Spacer(modifier = Modifier.height(30.dp))

                    // login button
                    NormalButton(
                        value = stringResource(id = R.string.login),
                        onClickAction = onLoginClick
                    )

                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                        ForgotPasswordText(
                            value = "Forgot",
                            onClickAction = { navController.navigate(User.ForgotPassword.route) }
                        )
                        ForgotPasswordText(
                            value = "Forgot password?",
                            onClickAction = { navController.navigate(User.ForgotPassword.route) }
                        )
                    }
                        ForgotPasswordText(
                            value = "Forgot password?",
                            onClickAction = { navController.navigate(User.ForgotPassword.route) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))
                Divider(modifier = Modifier.fillMaxWidth()) // divider
                // register text
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NormalTextComponent(
                        text = stringResource(id = R.string.register_your),
                        color = colorResource(id = R.color.softBlack)
                    )
                    RegisterTextComponent(
                        value = stringResource(id = R.string.account),
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun ForgotPasswordText(value: String, onClickAction: (Int) -> Unit) {
    ClickableText(
        text = AnnotatedString(value),
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 15.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        onClick = onClickAction,
    )
}

// account
@Composable
fun RegisterTextComponent(value: String, navController: NavController) {
    ClickableText(
        text = AnnotatedString(value),
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 5.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal
        ),
        onClick = {
            navController.navigate(User.Register.route)
        }
    )
}