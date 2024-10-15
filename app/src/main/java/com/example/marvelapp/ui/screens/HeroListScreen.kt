package com.example.marvelapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelapp.data.HeroModel
import com.example.marvelapp.data.HeroRepository
import com.example.marvelapp.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalConfiguration

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroListScreen(onHeroClick: (String) -> Unit) {
    val listState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //TriangleBack
        BackgroundTriangles()

        // Heroes
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Marvel logo
            Image(
                painter = painterResource(id = R.drawable.marvel_logo),
                contentDescription = "Marvel Logo",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )

            // H1
            Text(
                text = "Choose your hero",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            // Hero list
            LazyRow(
                state = listState,
                flingBehavior = snapFlingBehavior,
                modifier = Modifier.fillMaxSize(),
            ) {
                items(HeroRepository.heroes) { hero ->
                    HeroItem(hero = hero, onClick = { onHeroClick(hero.name) })
                }
            }
        }
    }
}

@Composable
fun BackgroundTriangles() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width, size.height)
                lineTo(size.width, size.height / 3)
                close()
            }
            drawPath(path, Color.Red)
        }
    }
}

@Composable
fun HeroItem(hero: HeroModel, onClick: () -> Unit) {
    // device info + vars
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val cardWidth = screenWidth * 0.85f
    val cardHeight = screenHeight * 0.7f
    val  cardPadding = 16.dp
    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(cardPadding)
    ) {
        // Hero image
        AsyncImage(
            model = hero.imageUrl,
            contentDescription = hero.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        // Hero Name
        Text(
            text = hero.name,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeroListScreen() {
    HeroListScreen(onHeroClick = {})
}