package com.example.excericecompose.coldplay.model

data class User(
    val id: Int,
    val name: String,
    val releaseDate: String,
    val songList: List<String>,
    val imageUrl: String
)