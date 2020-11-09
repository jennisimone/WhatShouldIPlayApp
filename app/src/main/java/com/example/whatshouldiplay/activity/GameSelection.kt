package com.example.whatshouldiplay.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre
import com.example.whatshouldiplay.repository.GameRepository

const val GAME = "game"

class GameSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_selection)
        populateSpinner()
    }

    fun getGame(view: View) {
        val randomGame: Game = getGameUsingSearchParams()

        val intent = Intent(this, GameNameView::class.java).apply {
            putExtra(GAME, randomGame.name)
        }
        startActivity(intent)
    }

    private fun getGameUsingSearchParams(): Game {
        val genreSpinner = findViewById<Spinner>(R.id.spinner)
        val multiplayerSwitch = findViewById<SwitchCompat>(R.id.switch1)
        val genre: String = genreSpinner.selectedItem.toString()
        val multiplayer = multiplayerSwitch.isChecked

        val randomGame: Game

        randomGame = when {
            genre != "ALL" -> {
                getGameFromSpecificGenre(Array(1) { genre.toString() })
            }
            multiplayer -> {
                getMultiplayerGame()
            }
            else -> {
                getRandomGame()
            }
        }
        return randomGame
    }

    private fun populateSpinner() {
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            getGenreValues()
        )
    }

    private fun getGenreValues(): MutableList<String> {
        val genres = Genre.values().toMutableList().map {
                genre -> genre.toString()
        }.toMutableList()
        genres.add(0, "ALL")

        return genres
    }

    private fun getRandomGame(): Game {
        val repo: GameRepository = GameRepository(this)
        val allGames = repo.getAllGames()
        allGames.shuffle()
        return allGames[0]
    }

    private fun getMultiplayerGame(): Game {
        val repo: GameRepository = GameRepository(this)
        val allGames = repo.getMultiPlayerGames()
        allGames.shuffle()
        return allGames[0]
    }

    private fun getGameFromSpecificGenre(genre: Array<String>): Game {
        val repo: GameRepository = GameRepository(this)
        val allGames = repo.getGamesByGenre(genre)
        allGames.shuffle()
        return allGames[0]
    }
}