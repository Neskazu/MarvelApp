package com.example.marvelapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroModel(
    val id: Int,
    val name: String,
    val description:String,
    val thumbnail: Thumbnail
)
{
    //Для удобства отображения
    fun getSmallDescription(): String
    {
        val maxWords=10
        val SmallDescription = description.split(" ").take(maxWords).joinToString(" ") + if (description.split(" ").size > maxWords) "..." else ""
        return  SmallDescription

    }
}
