package com.example.marvelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.marvelapp.ui.components.BackgroundTriangles
import com.example.marvelapp.ui.viewmodels.HeroViewModel

@Composable
fun HeroDetailScreen(viewModel: HeroViewModel, heroId: Int, navController: NavController) {
    LaunchedEffect(Unit) {
        viewModel.fetchHeroDetails(heroId)
    }
    // Hero by id
    val hero by viewModel.heroDetails.collectAsState()
    val errorMessage = viewModel.error.collectAsState().value
    Box (
        modifier = Modifier
            .fillMaxSize()
    ){
        if(errorMessage!=null)
        {
            BackgroundTriangles()
            Text(
                text = errorMessage.toString(),
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        else{
            //Возможно стоит немного поменять логику чтобы избавиться от it
            hero?.let {
                // Hero image
                AsyncImage(
                    model = it.thumbnail.getFullUrl(),
                    contentDescription = it.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                // Back button
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
                {
                    // Name
                    Text(
                        text = it.name,
                        color = Color.White,
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                    )

                    // Desc
                    Text(
                        text = it.getSmallDescription(),
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Left,
                        //так себе решение но outline ничем не лучше
                        style = TextStyle(
                            shadow = Shadow(
                                color = Color.Black,
                                blurRadius = 0f
                            )
                        )
                    )

                }
            }
        }
    }
}