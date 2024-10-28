package com.example.marvelapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.HeroModel
import com.example.marvelapp.data.HeroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeroViewModel(private val repository: HeroRepository) : ViewModel() {

    //Не уверен что это лучший вариант но пока так
    private val _heroList = MutableStateFlow<List<HeroModel>?>(null)
    val heroList: StateFlow<List<HeroModel>?> = _heroList

    private val _heroDetails = MutableStateFlow<HeroModel?>(null)
    val heroDetails: StateFlow<HeroModel?> = _heroDetails

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchHeroes(limit: Int) {
        viewModelScope.launch {
            Log.d("HeroViewModel", "fetchHeroes called")
            try {
                _heroList.value = repository.getHeroes(limit)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load heroes: ${e.localizedMessage}"
                Log.e("HeroViewModel", "Error fetching heroes", e)
            }
        }
    }

    fun fetchHeroDetails(heroId: Int) {
        viewModelScope.launch {
            try {
                _heroDetails.value = repository.getHeroDetails(heroId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Failed to load hero details: ${e.localizedMessage}"
            }
        }
    }
}
