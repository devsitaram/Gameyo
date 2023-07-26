package com.sitaram.gameyo.features.message

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sitaram.gameyo.ui.theme.Purple80
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MessageViewScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Message")
    }
}

// recycler
@Composable
fun MessageViewScreens() {

    val context = LocalContext.current
    var number by remember { mutableStateOf(0) }
    var inputNum by remember { mutableStateOf("") }

    val isNumberValid by remember(inputNum) {
        derivedStateOf {
            inputNum.isNotEmpty()
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // input text
                InputEditTextField(
                    inputNum,
                    onValueChange = { inputNum = it },
                    text = "Enter a number"
                )
                ButtonWithBorder(
                    onClickAction = {
                        if (isNumberValid) {
                            number = inputNum.toInt()
                        } else {
                            CoroutineScope(Dispatchers.Main).launch {
                                Toast.makeText(context, "The field is empty!", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                )
            }

            var i = 1
            if (number == 0) {
                Greeting(number)
            }
            while (i <= number) {
                Greeting(i)
                i++
            }
        }
    }
}

@Composable
fun Greeting(i: Int) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 70.dp else 0.dp
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(
                    "\uD83C\uDF3F  Leave",
                    fontSize = 30.sp,
                )
                Text(text = "$i")
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun ButtonWithBorder(onClickAction: () -> Unit) {
    Button(
        modifier = Modifier.padding(5.dp),
        onClick = onClickAction,
        border = BorderStroke(1.dp, Color.Gray),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
    ) {
        Text(text = "Find", color = Color.DarkGray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputEditTextField(value: String, onValueChange: (String) -> Unit = {}, text: String) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .width(250.dp)
                .height(70.dp)
                .padding(top = 5.dp),
            label = { Text(text) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Purple80,
                cursorColor = Purple80
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )
        // if the fields is empty then show error message
        if (value.isEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "The fields is empty!", color = Color.Red)
        }
    }
}