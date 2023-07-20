package com.sitaram.gameyo.features.forgotpassword

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sitaram.gameyo.R
import com.sitaram.gameyo.features.main.User
import com.sitaram.gameyo.features.util.InputTextField
import com.sitaram.gameyo.features.util.NormalButton
import com.sitaram.gameyo.features.util.PasswordTextField

@Composable
fun ForgotPasswordViewScreen(navController: NavController) {

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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                Modifier
                    .width(150.dp)
                    .height(150.dp),
                shape = RoundedCornerShape(300.dp),
                border = BorderStroke(2.dp, color = Color.Red),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null,
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .background(Color.Cyan),
                )
            }

            Text(
                text = "Forgot password?",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                color = Color.DarkGray
            )

            // email check
            Box(modifier = Modifier.wrapContentHeight()) {
                if (visible) {
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(15.dp)
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
                        NormalButton(value = "New password", onClickAction = onClickEmailCheck)
                    }
                } else {
                    // new password
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(15.dp)
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