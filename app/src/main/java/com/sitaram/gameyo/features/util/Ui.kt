package com.sitaram.gameyo.features.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sitaram.gameyo.R
import com.sitaram.gameyo.ui.theme.Purple80

// normal text
@Composable
fun NormalTextComponent(text: String, color: Color) {
    Text(
        text = text,
        modifier = Modifier
            .wrapContentHeight()
            .padding(horizontal = 5.dp),  // Specify the desired padding value
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
        color = color
    )
}

// heading text
@Composable
fun HeadingTextComponent(value: String, color: Color) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 10.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal
        ),
        color = color,
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    label: String,
    painterResource: Painter,
    isEmptyMessage: Boolean
) {
    // input text fields
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        // text fields bar's text
        label = {
            Text(label)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Purple80
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
    )
    // if the fields is empty then show error message
    if (!isEmptyMessage) {
        Text(text = "Enter the valid $label", style = TextStyle(fontSize = 12.sp), color = Color.Red)
    }
}

// password input text
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    painterResource: Painter,
    onValueChange: (String) -> Unit = {},
    label: String,
    isEmptyMessage: Boolean
) {
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        label = {
            Text(label)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Purple80
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        // lest side icon
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        // right side icon
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
//                    Icons.Filled.Visibility
                painterResource(R.drawable.ic_password_invisible)
            } else {
                painterResource(R.drawable.ic_password_visible)
            }
            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(painter = iconImage, contentDescription = null)
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
    )
    // if the fields is empty then show error message
    if (!isEmptyMessage) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Enter the valid $label", style = TextStyle(fontSize = 12.sp), color = Color.Red)
    }
}

// check box
@Composable
fun CheckboxComponent() {
    var checkedState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = it },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.DarkGray,
                uncheckedColor = Color.Gray
            )
        )
        Text(
            text = if (checkedState) "Remember" else "Remember Me"
        )
    }
}

// normal button
@Composable
fun NormalButton(value: String, onClickAction: () -> Unit) {
    Button(
        onClick = onClickAction,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
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