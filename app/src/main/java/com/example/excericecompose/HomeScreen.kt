package com.example.excericecompose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.excericecompose.coldplay.vm.ConversionVm

@Composable
fun HomeScreen(viewModel: ConversionVm,navController: NavController) {
    val cards by viewModel.cards.collectAsState()
    val expandedCardsId by viewModel.expandedCardIdsList.collectAsState()
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Button(
                onClick = {
                    // TODO
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Cyan
                ),
            ) {
                Text(
                    text = "Scroll to the top",
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = {
                    // TODO
                },
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Cyan
                ),
            ) {
                Text(
                    text = "Scroll to the end",
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(cards) { _, card ->
                ExpandableCard(
                    card = card,
                    onCardArrowClick = { viewModel.onCardArrowClicked(card.id) },
                    expanded = expandedCardsId.contains(card.id)
                )
            }
        }
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    HomeScreen(viewModel = viewModel(), navController = rememberNavController())
}