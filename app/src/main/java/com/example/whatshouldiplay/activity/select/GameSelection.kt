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
import com.example.whatshouldiplay.repository.GameRepository
import com.example.whatshouldiplay.repository.GenreRepository
import com.example.whatshouldiplay.repository.PlatformRepository
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

    private fun getGenreValues(): List<String> {
        val genreRepository = GenreRepository(this)
        return genreRepository.getAllGenres()
    }

    private fun getPlatformValues(): List<String> {
        val platformRepository = PlatformRepository(this)
        return platformRepository.getAllPlatforms()
    }

    private fun getRandomGame(): Game {
        val repo = GameRepository(this)
        var allGames = repo.getAllGames()

        allGames = allGames.toMutableList().shuffled()

        if (!allGames.isEmpty()) {
            return allGames[0]
        }
        return Game(0, "Boo 👻 no games found! Add some on the home screen!", "ADVENTURE", "MOBILE", false)
    }

    private fun getFilteredGame(multiplayer: Boolean, genre: Array<String>, platform: Array<String>): Game {
        val repo: GameRepository = GameRepository(this)
        var allGames = repo.getFilteredGames(multiplayer, genre, platform)

        allGames = allGames.toMutableList().shuffled()

        if (!allGames.isEmpty()) {
            return allGames[0]
        }
        return Game(0, "Sorry it looks like you don't have any games that specific.. 😰 Try a different search! ", "ADVENTURE", "MOBILE", false)
    }
}