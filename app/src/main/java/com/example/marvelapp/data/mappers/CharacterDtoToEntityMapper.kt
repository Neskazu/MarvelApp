package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.database.CharacterDTO
import com.example.marvelapp.data.database.CharacterEntity

fun CharacterDTO.toEntity() = CharacterEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    imageUrl = this.thumbnail.path.replaceFirst("http://", "https://") + ".${this.thumbnail.extension}"
)