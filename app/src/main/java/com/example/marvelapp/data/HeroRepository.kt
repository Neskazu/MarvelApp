package com.example.marvelapp.data

import com.example.marvelapp.network.MarvelApiService

//можно поменять логику обработки ошибок
class HeroRepository(private val api: MarvelApiService = MarvelApiService.create()) {
    suspend fun getHeroes(limit: Int): List<HeroModel> {
        val response = api.getHeroes(limit = limit)
        return if (response.isSuccessful) {
            response.body()?.data?.results ?: emptyList()
        } else {
            throw Exception("Error fetching heroes: ${response.message()}")
        }
    }

    suspend fun getHeroDetails(id: Int): HeroModel? {
        val response = api.getHeroDetails(characterId = id)
        return if (response.isSuccessful) {
            response.body()?.data?.results?.firstOrNull()
        } else {
            throw Exception("Error fetching hero details: ${response.message()}")
        }
    }
}