package com.example.marvelapp.data.models

import com.example.marvelapp.data.Thumbnail

data class CharacterUI (
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
)
{
    //возможно надо вынести отсюда
    fun getSmallDescription(): String
    {
        //это точно надо вынести куда нибудь в ресурсы
        val maxWords=10
        val SmallDescription = description.split(" ").take(maxWords).joinToString(" ") + if (description.split(" ").size > maxWords) "..." else ""
        return  SmallDescription

    }
}
