package com.example.excericecompose

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.request.RequestOptions
import com.example.excericecompose.model.User
import com.skydoves.landscapist.glide.GlideImage
import jp.wasabeef.transformers.glide.BlurTransformation

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
                painter = painterResource(id = R.drawable.ic_expand_more_24),
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

        Box(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        ) {
            GlideImage(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                requestOptions = {
                    RequestOptions().transform(
                        BlurTransformation(context = LocalContext.current, radius = 10, sampling = 1)
                    )
                },
                imageModel = card.imageUrl,
                contentScale = ContentScale.FillWidth,
                placeHolder = ImageBitmap.imageResource(id = R.drawable.image_placeholder),
                error = ImageBitmap.imageResource(id = R.drawable.image_not_found)
            )

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val (title,release_date,songs_title,songs) = createRefs()
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .constrainAs(title) {
                            centerHorizontallyTo(parent)
                        },
                    text = card.name,
                    color = Color.Magenta,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(
                        Font(R.font.hamer_medium)
                    )
                )

                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .constrainAs(songs_title) {
                            top.linkTo(title.bottom)
                        },
                    text = "List of songs: ",
                    color = Color.Magenta,
                    fontFamily = FontFamily(
                        Font(R.font.hamer_medium)
                    )
                )

                LazyVerticalGrid(
                    modifier = Modifier
                        .wrapContentHeight(unbounded = true)
                        .wrapContentWidth(unbounded = true)
                        .constrainAs(songs){
                            top.linkTo(songs_title.bottom)
                            start.linkTo(songs_title.start)
                        },
                    columns = GridCells.Fixed(2)
                ){
                    itemsIndexed(card.songList){ _, song ->
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .constrainAs(songs_title) {
                                    top.linkTo(title.bottom)
                                },
                            text = "$song ",
                            color = Color.Magenta,
                            fontFamily = FontFamily(
                                Font(R.font.hamer_medium)
                            )
                        )
                    }
                }
            }

        }
    }
}