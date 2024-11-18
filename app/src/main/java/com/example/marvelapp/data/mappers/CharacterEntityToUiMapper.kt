package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.database.CharacterEntity
import com.example.marvelapp.data.models.CharacterUI

fun CharacterEntity.toUIModel() = CharacterUI(
    id = this.id,
    name = this.name,
    description = this.description,
    imageUrl = this.imageUrl
)