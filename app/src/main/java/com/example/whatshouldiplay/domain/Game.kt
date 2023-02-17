package com.example.whatshouldiplay.domain

data class Game(val id: Long, val name: String, val genre: String, val platform: String, val completed: Boolean, val multiPlayer: Boolean)