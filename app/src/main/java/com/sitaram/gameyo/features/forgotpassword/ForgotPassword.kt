package com.sitaram.gameyo.features.forgotpassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sitaram.gameyo.R
import com.sitaram.gameyo.features.main.User
import com.sitaram.gameyo.features.util.InputTextField
import com.sitaram.gameyo.features.util.NormalButton
import com.sitaram.gameyo.features.util.PasswordTextField

// @Preview // navController: NavHostController
@Composable
fun ForgotPasswordViewScreen(navController: NavHostController) {

//    val navController = rememberNavController()

    val forgotPasswordViewModel = ForgotPasswordViewModel()
    val context = LocalContext.current

    var visible by remember { mutableStateOf(true) }

    val showDialog = remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isEmptyMessage by remember { mutableStateOf(true) }
    // check the empty text fields
    val isEmpty by remember {
        derivedStateOf {
            email.isEmpty() && password.isEmpty()
        }
    }

    val onClickEmailCheck: () -> Unit = {
        if (isEmpty) {
            isEmptyMessage = false // show error message
        } else {
            isEmptyMessage = true // hide error message
            val isValid = forgotPasswordViewModel.getUserEmail(email, context)
            if (isValid) {
                showDialog.value = true // dialog box
            }
        }
    }
    val onClickEmailChange: () -> Unit = {
        val isSuccess = forgotPasswordViewModel.getUserPassword(password, context)
        if (isSuccess) {
            navController.navigate(User.Login.route)
        }
    }

    Surface(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    },
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp),
                    )
                }
            }

            Column(
                modifier = Modifier.padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Forgot your password",
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    ),
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    text = "Don't worry enter your registered email id to received password reset option!",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Image(painter = painterResource(id = R.drawable.img_login), contentDescription = null, modifier = Modifier.fillMaxWidth())
            }

            // email check
            Box(modifier = Modifier.wrapContentHeight()) {
                if (visible) {
                    Column(modifier = Modifier.wrapContentWidth().padding(20.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(top = 40.dp))
                        InputTextField(
                            email,
                            onValueChange = { email = it },
                            label = stringResource(id = R.string.userEmail),
                            painterResource(R.drawable.ic_email),
                            isEmptyMessage = isEmptyMessage
                        )
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        NormalButton(value = "Send", onClickAction = onClickEmailCheck)
                    }
                } else {
                    // new password
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(20.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(top = 40.dp))
                        InputTextField(
                            password,
                            onValueChange = { password = it },
                            label = stringResource(id = R.string.userPassword),
                            painterResource(R.drawable.ic_lock),
                            isEmptyMessage = isEmptyMessage
                        )
                        PasswordTextField(
                            password,
                            onValueChange = { password = it },
                            painterResource = painterResource(id = R.drawable.ic_lock),
                            label = stringResource(id = R.string.userPassword),
                            isEmptyMessage = isEmptyMessage
                        )
                        Spacer(modifier = Modifier.padding(top = 30.dp))
                        NormalButton(
                            value = "Confirm password",
                            onClickAction = onClickEmailChange
                        )
                    }
                }

                // new design
            }
        }

        // show dialog box
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(stringResource(id = R.string.confirm)) },
                text = { Text(text = "Are you sure change your password?") },
                modifier = Modifier.fillMaxWidth(),
                dismissButton = {
                    TextButton(
                        onClick = { showDialog.value = false }
                    ) {
                        Text(text = "No")
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                            visible = false
                        }
                    ) {
                        Text(text = "Yes")
                    }
                }
            )
        }
    }
}