package com.example.marvelapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.CharacterRepository
import com.example.marvelapp.data.models.CharacterUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : ViewModel() {

    private val _characterList = MutableStateFlow<List<CharacterUI>?>(null)
    val characterList: StateFlow<List<CharacterUI>?> = _characterList

    private val _characterDetails = MutableStateFlow<CharacterUI?>(null)
    val characterDetails: StateFlow<CharacterUI?> = _characterDetails

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchCharacters(limit: Int) {
        viewModelScope.launch {
            try {
                _characterList.value = repository.getCharacters(limit)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load characters: ${e.localizedMessage}"
            }
        }
    }

    fun fetchCharacterDetails(characterId: Int) {
        viewModelScope.launch {
            try {
                _characterDetails.value = repository.getCharacterDetails(characterId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load character details: ${e.localizedMessage}"
            }
        }
    }
}
