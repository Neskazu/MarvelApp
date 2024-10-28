package com.example.marvelapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroApiResponse(
    val data: HeroData
)
@JsonClass(generateAdapter = true)
data class HeroData(
    val results: List<HeroModel>
)