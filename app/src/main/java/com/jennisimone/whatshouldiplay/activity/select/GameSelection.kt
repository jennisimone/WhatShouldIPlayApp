package com.jennisimone.whatshouldiplay.activity.select

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.add.AddGame
import com.jennisimone.whatshouldiplay.activity.library.LibrarySelection
import com.jennisimone.whatshouldiplay.domain.Game
import com.jennisimone.whatshouldiplay.repository.GameRepository
import com.jennisimone.whatshouldiplay.repository.GenreRepository
import com.jennisimone.whatshouldiplay.repository.PlatformRepository
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
        val completedSwitch = findViewById<SwitchCompat>(R.id.includeCompleted)
        val genre: String = genreSpinner.selectedItem.toString()
        val platform: String = platformSpinner.selectedItem.toString()
        val includeCompleted = completedSwitch.isChecked
        val multiplayer = multiplayerSwitch.isChecked

        val randomGame: Game

        randomGame = when {
            genre != "ALL" || platform != "ALL" || multiplayer || includeCompleted -> {
                getFilteredGame(includeCompleted, multiplayer, Array(1) { genre }, Array(1) { platform })
            }
            else -> {
                getRandomGame()
            }
        }
        return randomGame
    }

    private fun getGenreValues(): List<String> {
        val genreRepository = GenreRepository(this)
        val allGenres: MutableList<String> = genreRepository.getAllGenres().toMutableList()
        allGenres.add(0, "ALL")
        return allGenres
    }

    private fun getPlatformValues(): List<String> {
        val platformRepository = PlatformRepository(this)
        val allPlatforms = platformRepository.getAllPlatforms().toMutableList()
        allPlatforms.add(0, "ALL")
        return allPlatforms
    }

    private fun getRandomGame(): Game {
        val repo = GameRepository(this)
        var allGames = repo.getAllGames(false)

        allGames = allGames.toMutableList().shuffled()

        if (!allGames.isEmpty()) {
            return allGames[0]
        }
        return Game(0, "Boo 👻 no games found! Add some on the home screen!", "ADVENTURE", "MOBILE",
            completed = false,
            multiPlayer = false
        )
    }

    private fun getFilteredGame(includeCompleted: Boolean, multiplayer: Boolean, genre: Array<String>, platform: Array<String>): Game {
        val repo: GameRepository = GameRepository(this)
        var allGames = repo.getFilteredGames(includeCompleted, multiplayer, genre, platform)

        allGames = allGames.toMutableList().shuffled()

        if (!allGames.isEmpty()) {
            return allGames[0]
        }
        return Game(0, "Sorry it looks like you don't have any games that specific.. 😰 Try a different search! ", "ADVENTURE", "MOBILE",
            completed = false,
            multiPlayer = false
        )
    }

    fun addGame(item: MenuItem) {
        val intent = Intent(this, AddGame::class.java).apply {
        }
        startActivity(intent)
    }

    fun library(item: MenuItem) {
        val intent = Intent(this, LibrarySelection::class.java).apply {
        }
        startActivity(intent)
    }

    fun home(item: MenuItem) {
        val intent = Intent(this, MainActivity::class.java).apply {
        }
        startActivity(intent)
    }
}