package com.example.excericecompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserCard(name: String, releaseDate: String, imageUrl: String) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = name,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Release Date: $releaseDate",
                style = TextStyle(
                    color = Color.Gray
                )
            )
            Row {
                Spacer(modifier = Modifier.weight(1f))
                CardArrow(
                    degrees = 180f,
                ) {
                    // TODO
                }   
            }
            GlideImage(
                imageModel = imageUrl,
                contentScale = ContentScale.FillWidth,
                placeHolder = ImageBitmap.imageResource(id = R.drawable.image_placeholder),
                error = ImageBitmap.imageResource(id = R.drawable.image_not_found)
            )
        }
    }
}