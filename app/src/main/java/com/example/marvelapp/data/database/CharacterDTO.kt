package com.example.marvelapp.data.database

import com.example.marvelapp.data.Thumbnail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDTO(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)
