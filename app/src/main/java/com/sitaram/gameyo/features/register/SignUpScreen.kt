package com.sitaram.gameyo.features.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sitaram.gameyo.R
import com.sitaram.gameyo.features.main.User
import com.sitaram.gameyo.features.util.HeadingTextComponent
import com.sitaram.gameyo.features.util.InputTextField
import com.sitaram.gameyo.features.util.NormalTextComponent
import com.sitaram.gameyo.features.util.PasswordTextField

// Main/Parent UI design for Sign Up Screen
@Composable
fun SignUpViewScreen(navController: NavHostController) {

    val context = LocalContext.current
    var email by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isEmptyMessage by remember { mutableStateOf(true) }
    // check the empty text fields
    val isEmpty by remember {
        derivedStateOf {
            email.isEmpty() && name.isEmpty() && password.isEmpty()
        }
    }

    // on click function
    val onClickAction: () -> Unit = {
        if (isEmpty) {
            isEmptyMessage = false // show error message
        } else {
            isEmptyMessage = true // hide error message
            val signUpViewModel = SignUpViewModel()
            val isValidRegister = signUpViewModel.registerDetail(email, name, password, context)
            if (isValidRegister) {
                navController.navigate(User.Login.route)
            }
        }
    }

    // sign screen page
    Surface(
        modifier = Modifier
            .fillMaxSize() // size
            .background(Color.White) // background
            .padding(30.dp) // padding
    ) {
        // child layout file
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally // gravity center
        ) {
            NormalTextComponent(
                text = stringResource(id = R.string.hey),
                color = colorResource(id = R.color.softBlack)
            )

            HeadingTextComponent(
                value = stringResource(id = R.string.create_an_account),
                color = colorResource(id = R.color.black)
            )

            Column(modifier = Modifier.padding(top = 40.dp)) {
                // email
                InputTextField(
                    email,
                    onValueChange = { email = it },
                    label = stringResource(id = R.string.userEmail),
                    painterResource(R.drawable.ic_email),
                    isEmptyMessage = isEmptyMessage
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                // username
                InputTextField(
                    name,
                    onValueChange = { name = it },
                    label = stringResource(id = R.string.userName),
                    painterResource(R.drawable.ic_person),
                    isEmptyMessage = isEmptyMessage
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                // password
                PasswordTextField(
                    password,
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.userPassword),
                    isEmptyMessage = isEmptyMessage
                )

                Spacer(modifier = Modifier.height(30.dp))

                // button
                RegisterButton(
                    value = stringResource(id = R.string.signup),
                    isEnabled = isEmpty,
                    onClickAction = onClickAction,
                )
            }
            Spacer(modifier = Modifier.height(70.dp))
            Divider(modifier = Modifier.fillMaxWidth()) // using the divider
            // register text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                NormalTextComponent(
                    text = stringResource(id = R.string.login_your),
                    color = colorResource(id = R.color.softBlack)
                )
                LoginTextComponent(
                    text = stringResource(id = R.string.account),
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RegisterButton(value: String, isEnabled: Boolean = false, onClickAction: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentPadding = PaddingValues(15.dp),
        enabled = isEnabled,
        onClick = onClickAction,
    ) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// account
@Composable
fun LoginTextComponent(text: String, navController: NavHostController) {
    ClickableText(
        text = AnnotatedString(text),
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 5.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal
        ),

        onClick = {
            navController.navigate(User.Login.route)
        },
    )
}