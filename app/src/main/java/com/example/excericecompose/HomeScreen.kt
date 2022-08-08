package com.example.excericecompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Test") },
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide()})
        )

        Text(
            text = "It's a test one",
            modifier = Modifier.padding(top = 15.dp)
        )

        Button(
            onClick = {
                navController.navigate(Screen.Dashboard.passArgument(textState.value.text))
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan
            ),
            modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Enter",
                color = Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}