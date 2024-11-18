package com.example.marvelapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelapp.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.example.marvelapp.ui.components.BackgroundTriangles
import com.example.marvelapp.ui.components.HeroItem
import com.example.marvelapp.ui.viewmodels.HeroViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroListScreen(viewModel: HeroViewModel, onHeroClick: (Int) -> Unit) {
    //init values
    val listState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    // Вызов fetch при первом запуске
    LaunchedEffect(Unit) {
        viewModel.fetchHeroes(11) // надо в ресурсах создать переменную
    }
    //по идее можно как в details сделать не знаю какой вариант лучше
    val heroesFromApi = viewModel.heroList.collectAsState().value ?: emptyList()
    val errorMessage = viewModel.error.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //TriangleBack
        BackgroundTriangles()
        //Понятия не имею как сделать это иначе(лучше)
        if(errorMessage!=null)
        {
            Text(
                text = errorMessage.toString(),
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        else
        {
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
                    text = stringResource(R.string.choose_hero),
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                // Hero list
                LazyRow(
                    state = listState,
                    verticalAlignment = Alignment.CenterVertically,
                    flingBehavior = snapFlingBehavior,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(heroesFromApi) { hero ->
                        HeroItem(hero = hero, onClick = { onHeroClick(hero.id) })
                    }
                }
            }
        }
    }
}