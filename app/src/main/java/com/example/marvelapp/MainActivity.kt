package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.data.HeroRepository
import com.example.marvelapp.network.MarvelApiService
import com.example.marvelapp.ui.screens.HeroDetailScreen
import com.example.marvelapp.ui.screens.HeroListScreen
import com.example.marvelapp.ui.viewmodels.HeroViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ToDo: сделать фабрики?
        // Инициализация MarvelApiService
        val apiService = MarvelApiService.create()
        // Инициализация HeroRepository с использованием apiService
        val repository = HeroRepository(apiService)
        val viewModel = HeroViewModel(repository)
        setContent {
            val navController = rememberNavController()
            MarvelNavHost(navController, viewModel)
        }
    }
}

@Composable
fun MarvelNavHost(navController: NavHostController, viewModel: HeroViewModel) {
    NavHost(navController = navController, startDestination = "heroList") {
        composable("heroList") {
            HeroListScreen(viewModel = viewModel, onHeroClick = { heroId ->
                navController.navigate("heroDetail/$heroId")
            })
        }
        composable("heroDetail/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull()
            if (heroId != null) {
                HeroDetailScreen(viewModel = viewModel,heroId = heroId, navController = navController)
            }
        }
    }
}