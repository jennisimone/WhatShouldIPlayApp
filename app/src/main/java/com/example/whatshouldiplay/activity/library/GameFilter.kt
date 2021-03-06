package com.example.whatshouldiplay.activity.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.domain.Genre
import com.example.whatshouldiplay.domain.Platform
import kotlinx.android.synthetic.main.activity_add_game.*

class GameFilter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_filter)
        populateGenreSpinner()
        populatePlatformSpinner()
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
}