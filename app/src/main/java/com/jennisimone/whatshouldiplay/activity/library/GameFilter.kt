package com.jennisimone.whatshouldiplay.activity.library

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.add.AddGame
import com.jennisimone.whatshouldiplay.repository.GenreRepository
import com.jennisimone.whatshouldiplay.repository.PlatformRepository
import kotlinx.android.synthetic.main.activity_add_game.*

class GameFilter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_filter)
        populateGenreSpinner()
        populatePlatformSpinner()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.menu.getItem(3).isChecked = true
    }

    fun getGame(view: View) {
        val genreSpinner = findViewById<Spinner>(R.id.genreSpinner)
        val multiplayerSwitch = findViewById<SwitchCompat>(R.id.switch1)
        val genre: String = genreSpinner.selectedItem.toString()
        val platform: String = platformSpinner.selectedItem.toString()
        val multiplayer = multiplayerSwitch.isChecked

        val intent = Intent(this, GameLibrary::class.java).apply {
            putExtra("filtered", true)
            putExtra("multiplayer", multiplayer)
            putExtra("genre", arrayOf(genre))
            putExtra("platform", arrayOf(platform))
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

    private fun getGenreValues(): List<String> {
        val genreRepository = GenreRepository(this)
        return genreRepository.getAllGenres()
    }

    private fun getPlatformValues(): List<String> {
        val platformRepository = PlatformRepository(this)
        return platformRepository.getAllPlatforms()
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