package com.jennisimone.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.library.LibrarySelection
import com.jennisimone.whatshouldiplay.activity.select.GAME
import com.jennisimone.whatshouldiplay.domain.Game
import com.jennisimone.whatshouldiplay.repository.GameRepository
import com.jennisimone.whatshouldiplay.repository.GenreRepository
import com.jennisimone.whatshouldiplay.repository.PlatformRepository

class AddGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        populateGenreSpinner()
        populatePlatformSpinner()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val addGameItem = bottomNav.menu.getItem(2)
        addGameItem.isChecked = true
    }

    fun addGame(view: View) {
        val repo = GameRepository(this)

        val gameNameEditText: EditText = findViewById(R.id.editTextGameName)
        val genreSpinner: Spinner = findViewById(R.id.genreSpinner)
        val platformSpinner: Spinner = findViewById(R.id.platformSpinner)
        val multiplayerChip: SwitchCompat = findViewById(R.id.multiplayer)
        val completedChip: SwitchCompat = findViewById(R.id.completed)

        val gameName = gameNameEditText.text.toString()
        val genre = genreSpinner.selectedItem.toString()
        val platform = platformSpinner.selectedItem.toString()
        val completed = completedChip.isChecked
        val multiplayer = multiplayerChip.isChecked

        repo.add(Game(0, gameName, genre, platform, completed, multiplayer))

        val intent = Intent(this, GameAdded::class.java).apply {
            putExtra(GAME, gameName)
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