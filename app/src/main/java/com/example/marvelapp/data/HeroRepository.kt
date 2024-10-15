package com.example.marvelapp.data

object HeroRepository {
    val heroes = listOf(
        HeroModel(
            name = "Iron Man",
            imageUrl = "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/2dd5e5125369651.6117b731d0915.jpg",
            description = "A wealthy American business magnate, playboy, and ingenious scientist."
        ),
        HeroModel(
            name = "Captain America",
            imageUrl = "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/2dd5e5125369651.6117b731d0915.jpg",
            description = "A World War II veteran who was enhanced to the peak of human physicality."
        ),
        // Добавьте других героев
    )
}