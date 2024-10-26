package com.example.marvelapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelapp.data.HeroModel
import com.example.marvelapp.ui.utils.Paddings
import com.example.marvelapp.ui.utils.Sizes
import com.example.marvelapp.ui.utils.screenHeightDp
import com.example.marvelapp.ui.utils.screenWidthDp

@Composable
fun HeroItem(hero: HeroModel, onClick: () -> Unit) {
    val cardWidth = screenWidthDp() * (Sizes.CardWidthFactor)
    val cardHeight = screenHeightDp() * (Sizes.CardHeightFactor)
    val  cardPadding = (Paddings.CardPadding)
    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .padding(cardPadding)
            .clickable { onClick() }
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
