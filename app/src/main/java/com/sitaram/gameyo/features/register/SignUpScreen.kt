package com.sitaram.gameyo.features.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sitaram.gameyo.R
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import com.sitaram.gameyo.features.main.User
import com.sitaram.gameyo.features.util.HeadingTextComponent
import com.sitaram.gameyo.features.util.InputTextField
import com.sitaram.gameyo.features.util.NormalTextComponent
import com.sitaram.gameyo.features.util.PasswordTextField

// Main/Parent UI design for Sign Up Screen
//@Preview // navController: NavHostController
@Composable
fun SignUpViewScreen(navController: NavHostController) {

//    val navController = rememberNavController()
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
            email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()
        }
    }

    // on click function
    val onClickAction: () -> Unit = {
        if (isEmpty) {
            isEmptyMessage = true // show error message
            val signUpViewModel = SignUpViewModel()
            val isValidRegister = signUpViewModel.registerDetail(email, name, password, context)
            if (isValidRegister) {
                navController.navigate(User.Login.route)
            }
        } else {
            isEmptyMessage = false // hide error message
        }
    }

    // sign screen page
    Surface(
        modifier = Modifier
            .fillMaxSize() // size
            .background(Color.White) // background
            .padding(10.dp) // padding
    ) {
        // child layout file
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier.size(40.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            NormalTextComponent(
                text = "Sing Up",
                color = colorResource(id = R.color.softBlack)
            )

            HeadingTextComponent(
                value = stringResource(id = R.string.create_an_account),
                color = colorResource(id = R.color.black)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))
            Column(modifier = Modifier.padding(20.dp)) {
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
                    onClickAction = onClickAction,
                )
                TextComponents(text = "By registering, you confirm that your accept our team of Use and Privacy policy.", color = colorResource(id = R.color.softBlack))
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
                    text = stringResource(id = R.string.already_account),
                    color = colorResource(id = R.color.softBlack)
                )
                LoginTextComponent(
                    text = stringResource(id = R.string.sign_in),
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RegisterButton(value: String, onClickAction: () -> Unit) {
//    Button(
//        onClick = onClickAction,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp),
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue) // Change the button color here
//    ) {
//        Text(
//            fontSize = 20.sp,
//            text = value,
//            color = Color.White,
//            fontWeight = FontWeight.SemiBold,
//        )
//    }
    Button(
        onClick = onClickAction,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
    ) {
        Text(
            fontSize = 15.sp,
            text = value,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(5.dp),
            color = Color.White
        )
    }
}

// account
@Composable
fun LoginTextComponent(text: String, navController: NavHostController) {
    ClickableText(
        text = AnnotatedString(text),
        modifier = Modifier
            .wrapContentHeight(),
        style = TextStyle(
            color = Color.Blue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        onClick = {
            navController.navigate(User.Login.route)
        },
    )
}

@Composable
fun TextComponents(text: String, color: Color) {
    Text(
                text = text,
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 5.dp),  // Specify the desired padding value
        style = TextStyle(
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = color
    )
}