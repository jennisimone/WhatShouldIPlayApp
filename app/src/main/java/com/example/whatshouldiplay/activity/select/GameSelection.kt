package com.example.whatshouldiplay.activity.select

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
import com.example.whatshouldiplay.domain.Platform
import com.example.whatshouldiplay.repository.GameRepository
import kotlinx.android.synthetic.main.activity_add_game.*

const val GAME = "game"

class GameSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_selection)
        populateGenreSpinner()
        populatePlatformSpinner()
    }

    fun getGame(view: View) {
        val randomGame: Game = getGameUsingSearchParams()

        val intent = Intent(this, GameNameView::class.java).apply {
            putExtra(GAME, randomGame.name)
        }
        startActivity(intent)
    }

    private fun populateGenreSpinner() {
        val spinner: Spinner = findViewById(R.id.genreSpinner)
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            getGenreValues()
        )
    }

    private fun populatePlatformSpinner() {
        val spinner: Spinner = findViewById(R.id.platformSpinner)
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            getPlatformValues()
        )
    }

    private fun getGameUsingSearchParams(): Game {
        val genreSpinner = findViewById<Spinner>(R.id.genreSpinner)
        val multiplayerSwitch = findViewById<SwitchCompat>(R.id.switch1)
        val genre: String = genreSpinner.selectedItem.toString()
        val platform: String = platformSpinner.selectedItem.toString()
        val multiplayer = multiplayerSwitch.isChecked

        val randomGame: Game

        randomGame = when {
            genre != "ALL" || platform != "ALL" || multiplayer -> {
                getFilteredGame(multiplayer, Array(1) { genre }, Array(1) { platform })
            }
            else -> {
                getRandomGame()
            }
        }
        return randomGame
    }

    private fun getGenreValues(): MutableList<String> {
        val genres = Genre.values().toMutableList().map {
                genre -> genre.toString()
        }.toMutableList()
        genres.add(0, "ALL")

        return genres
    }

    private fun getPlatformValues(): MutableList<String> {
        val platforms = Platform.values().toMutableList().map {
                genre -> genre.toString()
        }.toMutableList()
        platforms.add(0, "ALL")

        return platforms
    }

    private fun getRandomGame(): Game {
        val repo: GameRepository = GameRepository(this)
        val allGames = repo.getAllGames()
        allGames.toMutableList().shuffle()
        return allGames[0]
    }

    private fun getFilteredGame(multiplayer: Boolean, genre: Array<String>, platform: Array<String>): Game {
        val repo: GameRepository = GameRepository(this)
        val allGames = repo.getFilteredGames(multiplayer, genre, platform)
        allGames.toMutableList().shuffle()
        return allGames[0]
    }
}