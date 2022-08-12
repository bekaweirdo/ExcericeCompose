package com.example.excericecompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserCard(name: String, imageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(start = 10.dp),
            imageModel = imageUrl,
            contentScale = ContentScale.FillWidth,
            placeHolder = ImageBitmap.imageResource(id = R.drawable.image_placeholder),
            error = ImageBitmap.imageResource(id = R.drawable.image_not_found)
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = name,
            style = TextStyle(
                color = Color.White
            )
        )
    }
}