package com.example.marvelapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelapp.data.HeroRepository
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController

@Composable
fun HeroDetailScreen(heroId: Int, navController: NavController) {
    // Hero by id
    val hero = HeroRepository.heroes.find { it.id == heroId }

    hero?.let {
        Box (
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Hero image
            AsyncImage(
                model = it.imageUrl,
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
                    text = hero.name,
                    color = Color.White,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                )

                // Desc
                Text(
                    text = hero.description,
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}