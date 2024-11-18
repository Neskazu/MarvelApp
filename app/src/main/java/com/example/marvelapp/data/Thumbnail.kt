package com.example.marvelapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val  extension: String,
)
{
    fun getFullUrl(): String
    {
        //В теории можно дать разрешение на http, но как будто так лучше
        val fullUrl = path.replaceFirst("http://", "https://") + ".$extension"
        println("Image URL: $fullUrl")
        return fullUrl
    }
}