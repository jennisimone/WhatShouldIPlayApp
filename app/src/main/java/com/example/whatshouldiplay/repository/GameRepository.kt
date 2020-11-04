package com.example.whatshouldiplay.repository

import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre

class GameRepository {
    fun add(game: Game) {
        // save somewhere
    }

    fun getAllGames() {
        // return all games
    }

    fun getMultiPlayerGames() {
        // return multi player games
    }

    fun getGamesByGenre(genre: Genre, multiPlayerRequired: Boolean) {
        // return games from that genre, with multi player requirement
    }
}