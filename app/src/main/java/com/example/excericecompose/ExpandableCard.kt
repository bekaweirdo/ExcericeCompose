package com.example.excericecompose

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.excericecompose.model.User
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ExpandableCard(
    card: User,
    onCardArrowClick: () -> Unit,
    expanded: Boolean
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }

    val transition = updateTransition(targetState = transitionState, label = "transition")

    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = 450)
    }, label = "rotationDegreeTransition") {
        if (it.currentState == expanded) 0f else 180f
    }

    Card(
        backgroundColor = Color.Cyan,
        contentColor = Color.DarkGray,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardArrow(
                degrees = arrowRotationDegree,
                onClick = onCardArrowClick
            )
            Column(Modifier.weight(2f)) {
                Text(
                    text = card.name,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = card.releaseDate,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            GlideImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                imageModel = card.imageUrl,
                contentScale = ContentScale.FillWidth,
                placeHolder = ImageBitmap.imageResource(id = R.drawable.image_placeholder),
                error = ImageBitmap.imageResource(id = R.drawable.image_not_found)
            )
        }
        ExpandableContent(card, visible = expanded)
    }
}

@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less_24),
                contentDescription = "Expendable Arrow",
                modifier = Modifier.rotate(degrees)
            )
        }
    )
}

@Composable
fun ExpandableContent(
    card: User,
    visible: Boolean = true
) {
    var screenWidthPx: Int
    var screenWidthDp: Int
    var cardWidthPx: Int
    var cardWidthDp: Int
    var cardHeightPx: Int
    val cardHeightDp = 150
    var cardMarginPx: Int
    val cardMarginDp = 15

    with(LocalDensity.current) {
        screenWidthDp = LocalConfiguration.current.screenWidthDp
        screenWidthPx = screenWidthDp.dp.toPx().toInt()
        cardWidthPx = screenWidthPx / 2
        cardWidthDp = screenWidthDp / 2
        cardHeightPx = cardHeightDp.dp.toPx().toInt()
        cardMarginPx = cardMarginDp.dp.toPx().toInt()
    }

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(450)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(450)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(450)
        ) + fadeOut(
            animationSpec = tween(450)
        )
    }

    AnimatedVisibility(
        visible = visible,
        enter = enterTransition,
        exit = exitTransition
    ) {
        val scrollState = rememberScrollState()

        val bg = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(
                LocalContext.current.resources,
                R.drawable.ic_launcher_background
            ), screenWidthPx, screenWidthPx, true
        )
        val bgBlurred = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(
                LocalContext.current.resources,
                R.drawable.ic_launcher_background
            ), screenWidthPx, screenWidthPx, true
        )

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
//            GlideImage(
//                modifier = Modifier
//                    .wrapContentWidth()
//                    .wrapContentHeight(),
//                imageModel = card.imageUrl,
//                contentScale = ContentScale.FillWidth,
//                placeHolder = ImageBitmap.imageResource(id = R.drawable.image_placeholder),
//                error = ImageBitmap.imageResource(id = R.drawable.image_not_found)
//            )
            Image(
                bitmap = bgBlurred.asImageBitmap(),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )

            Canvas(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .width((10 * (cardWidthDp + cardMarginDp) + cardMarginDp).dp)
                    .height(screenWidthDp.dp)
            ) {
                for (i in 0..10) {
                    val path = Path()

                    path.addRoundRect(
                        RoundRect(
                            Rect(
                                Offset(
                                    (i * (cardWidthPx + cardMarginPx) + cardMarginPx).toFloat(),
                                    0f
                                ),
                                Size(cardWidthPx.toFloat(), cardHeightPx.toFloat())
                            ),
                            CornerRadius(10.dp.toPx())
                        )
                    )

                    clipPath(path, clipOp = ClipOp.Intersect) {
                        drawImage(
                            bg.asImageBitmap(),
                            Offset(10f, 0f)
                        )
                    }
                }
            }

//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//                Text(
//                    modifier = Modifier.padding(start = 10.dp),
//                    text = card.name,
//                    style = TextStyle(
//                        color = Color.White
//                    )
//                )
//
//            }
        }
    }
}