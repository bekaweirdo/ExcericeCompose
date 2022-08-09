package com.example.excericecompose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DashboardScreen(navController: NavController, viewModel: ConversionVm, value: Int) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (fahrenheitText, calculateButton) = createRefs()
        Text(
            text = viewModel.fahrenheitConverter(value),
            modifier = Modifier
                .padding(top = 15.dp)
                .constrainAs(
                    fahrenheitText
                ) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            style = TextStyle(
                fontSize = 25.sp
            )
        )
        Button(
            onClick = {
                navController.navigateUp()
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan
            ),
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .constrainAs(calculateButton) {
                    top.linkTo(fahrenheitText.bottom)
                    start.linkTo(fahrenheitText.start)
                    end.linkTo(fahrenheitText.end)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = "Calculate Again",
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview()
@Composable
fun DashboardPreview() {
    DashboardScreen(navController = rememberNavController(), ConversionVm(),10)
}