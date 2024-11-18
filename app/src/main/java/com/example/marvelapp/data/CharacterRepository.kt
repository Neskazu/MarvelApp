package com.example.marvelapp.data

import com.example.marvelapp.data.database.CharacterDao
import com.example.marvelapp.data.mappers.toEntity
import com.example.marvelapp.data.mappers.toUIModel
import com.example.marvelapp.data.models.CharacterUI
import com.example.marvelapp.network.MarvelApiService

class CharacterRepository(
    private val api: MarvelApiService,
    private val dao: CharacterDao
)
{
    suspend fun getCharacters(limit: Int): List<CharacterUI> {
        val localData = dao.getAllCharacters().map { it.toUIModel() }
        if (localData.isEmpty()) {
            val response = api.getCharacters(limit = limit)
            if (response.isSuccessful()) {
                val networkData = response.body()?.data?.results ?: emptyList()
                val entities = networkData.map { it.toEntity() }
                //save to local db
                dao.insertCharacters(entities)
                return entities.map { it.toUIModel() }
            } else {
                throw Exception("Error fetching characters: ${response.message()}")
            }
        }
        return localData
    }

    suspend fun getCharacterDetails(id: Int): CharacterUI? {
        val localCharacterUI = dao.getCharacterById(id).toUIModel()
        if (localCharacterUI == null) {
            val response = api.getCharcterDetails(characterId = id)
            if (response.isSuccessful()) {
                val character = response.body()?.data?.results?.firstOrNull()
                character?.let {
                    dao.insertCharacter(it.toEntity())
                }
                return character?.toUIModel()
            }else{
                throw Exception("Error fetching heroes: ${response.message()}")
            }
        }
        return localCharacterUI
    }
}


