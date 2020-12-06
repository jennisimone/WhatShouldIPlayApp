package com.example.whatshouldiplay.activity.library

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.activity.select.GAME
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.repository.GameRepository

class GameDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        val game: Game = getGame()
    }

    private fun getGame(): Game {
        val gameName = intent.getStringExtra(GAME)
        val gameRepository: GameRepository = GameRepository(this)
        return gameRepository.getGame(gameName.orEmpty())
    }


}