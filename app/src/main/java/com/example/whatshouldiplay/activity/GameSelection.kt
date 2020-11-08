package com.example.whatshouldiplay.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre
import com.example.whatshouldiplay.repository.GameRepository
import kotlin.random.Random

const val GAME = "game"

class GameSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_selection)
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Genre.values()
        )
    }

    fun getGame(view: View) {
        val randomGame = getRandomGame()
        val intent = Intent(this, GameNameView::class.java).apply {
            putExtra(GAME, randomGame.name)
        }
        startActivity(intent)
    }

    fun getRandomGame(): Game {
        val repo: GameRepository = GameRepository(this)
        val allGames = repo.getAllGames()

        return allGames[Random(allGames.size).nextInt()]
    }
}