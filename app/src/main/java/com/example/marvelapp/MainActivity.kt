package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Dao
import androidx.room.Room
import com.example.marvelapp.data.CharacterRepository
import com.example.marvelapp.data.database.AppDatabase
import com.example.marvelapp.data.database.CharacterDao
import com.example.marvelapp.network.MarvelApiService
import com.example.marvelapp.ui.screens.HeroDetailScreen
import com.example.marvelapp.ui.screens.HeroListScreen
import com.example.marvelapp.ui.viewmodels.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ToDo: сделать фабрики?
        // Инициализация MarvelApiService
        val apiService = MarvelApiService.create()
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "marvelDB"
        ).build()
        // get CharacterDao
        val characterDao = database.characterDao()
        //init rep+view
        val repository = CharacterRepository(apiService,characterDao)
        val viewModel = CharacterViewModel(repository);
        setContent {
            val navController = rememberNavController()
            MarvelNavHost(navController, viewModel)
        }
    }
}

@Composable
fun MarvelNavHost(navController: NavHostController, viewModel: CharacterViewModel) {
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