package com.example.whatshouldiplay.domain

data class Game(val id: Long, val name: String, val genre: Genre, val platform: String, val multiPlayer: Boolean)