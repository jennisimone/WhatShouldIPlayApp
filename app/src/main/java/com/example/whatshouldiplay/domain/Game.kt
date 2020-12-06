package com.example.whatshouldiplay.domain

data class Game(val id: Long, val name: String, val genre: Genre, val platform: Platform, val multiPlayer: Boolean)