package com.example.whatshouldiplay.activity.library

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.activity.select.GAME
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre
import com.example.whatshouldiplay.repository.GameRepository
import com.example.whatshouldiplay.repository.PlatformRepository

class GameDetail : AppCompatActivity() {
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)

        populateGenreSpinner()
        populatePlatformSpinner()

        game = getGame()
        populateForm()
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
        val multiplayerChip: SwitchCompat = findViewById(R.id.multiplayer)

        val gameName = gameNameEditText.text.toString()
        val genre = Genre.valueOf(genreSpinner.selectedItem.toString())
        val platform = platformSpinner.selectedItem.toString()
        val multiplayer = multiplayerChip.isChecked

        game = game.copy(name = gameName,  genre = genre, platform = platform, multiPlayer = multiplayer)
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
            setSelection(Genre.values().indexOf(game.genre))
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
            Genre.values()
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
}