package com.example.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.ui.screens.HeroListScreen
import com.example.marvelapp.ui.screens.HeroDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MarvelNavHost(navController)
        }
    }
}

@Composable
fun MarvelNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "heroList") {
        composable("heroList") {
            HeroListScreen(onHeroClick = { heroId ->
                navController.navigate("heroDetail/$heroId")
            })
        }

        composable("heroDetail/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull()
            if (heroId != null) {
                HeroDetailScreen(heroId = heroId, navController = navController)
            }
        }
    }
}