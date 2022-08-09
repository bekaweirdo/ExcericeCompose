package com.example.excericecompose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: ConversionVm) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        val keyboardController = LocalSoftwareKeyboardController.current
        val (button, edittext) = createRefs()

        OutlinedTextField(value = textState.value,
            onValueChange = { textState.value = it },
            label = { Text("Fahrenheit") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            modifier = Modifier.constrainAs(edittext) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )

        Button(
            onClick = {
                navController.navigate(
                    Screen.Dashboard.passArgument(
                        textState.value.text.toInt()
                    )
                )
            },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan
            ),
            modifier = Modifier
                .padding(top = 10.dp)
                .constrainAs(button) {
                    top.linkTo(edittext.bottom)
                    start.linkTo(edittext.start)
                    end.linkTo(edittext.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = "Calculate",
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    HomeScreen(navController = rememberNavController(), ConversionVm())
}