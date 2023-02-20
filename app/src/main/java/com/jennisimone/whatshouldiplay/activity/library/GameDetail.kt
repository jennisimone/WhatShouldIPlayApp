package com.jennisimone.whatshouldiplay.activity.library

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.add.AddGame
import com.jennisimone.whatshouldiplay.activity.select.GAME
import com.jennisimone.whatshouldiplay.domain.Game
import com.jennisimone.whatshouldiplay.repository.GameRepository
import com.jennisimone.whatshouldiplay.repository.GenreRepository
import com.jennisimone.whatshouldiplay.repository.PlatformRepository

class GameDetail : AppCompatActivity() {
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        populateGenreSpinner()
        populatePlatformSpinner()

        game = getGame()
        populateForm()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.menu.getItem(3).isChecked = true
    }

    fun save(view: View) {
        updateGame()
        val gameRepository = GameRepository(this)
        gameRepository.amend(game)

        this.finish()
    }

    private fun updateGame() {
        val gameNameEditText: EditText = findViewById(R.id.editTextGameName)
        val genreSpinner: Spinner = findViewById(R.id.genreSpinner)
        val platformSpinner: Spinner = findViewById(R.id.platformSpinner)
        val completedChip: SwitchCompat = findViewById(R.id.completed)
        val multiplayerChip: SwitchCompat = findViewById(R.id.multiplayer)

        val gameName = gameNameEditText.text.toString()
        val genre = genreSpinner.selectedItem.toString()
        val platform = platformSpinner.selectedItem.toString()
        val completed = completedChip.isChecked
        val multiplayer = multiplayerChip.isChecked

        game = game.copy(name = gameName,  genre = genre, platform = platform, completed = completed, multiPlayer = multiplayer)
    }

    fun delete(view: View) {
        val gameRepository = GameRepository(this)
        gameRepository.deleteGame(arrayOf(game.name))

        this.finish()
    }

    private fun populateForm() {
        findViewById<TextView>(R.id.editTextGameName).apply {
            text = game.name
        }
        findViewById<Spinner>(R.id.genreSpinner).apply {
            setSelection(getGenreValues().indexOf(game.genre))
        }
        findViewById<Spinner>(R.id.platformSpinner).apply {
            setSelection(getPlatformValues().indexOf(game.platform))
        }
        findViewById<SwitchCompat>(R.id.multiplayer).apply {
            isChecked = game.multiPlayer
        }
    }


    private fun getGame(): Game {
        val gameName = intent.getStringExtra(GAME)
        val gameRepository: GameRepository = GameRepository(this)
        return gameRepository.getGame(gameName.orEmpty())
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

    private fun getPlatformValues(): List<String> {
        val platformRepository = PlatformRepository(this)
        return platformRepository.getAllPlatforms()
    }

    private fun getGenreValues(): List<String> {
        val genreRepository = GenreRepository(this)
        return genreRepository.getAllGenres()
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