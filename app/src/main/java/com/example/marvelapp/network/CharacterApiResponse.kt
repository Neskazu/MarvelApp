package com.example.marvelapp.network

import com.example.marvelapp.data.database.CharacterDTO
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterApiResponse(
    val data: CharacterData
)

@JsonClass(generateAdapter = true)
data class CharacterData(
    val results: List<CharacterDTO>
)
