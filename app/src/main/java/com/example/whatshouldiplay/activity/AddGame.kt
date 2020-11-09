package com.example.whatshouldiplay.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre
import com.example.whatshouldiplay.repository.GameRepository
import com.google.android.material.chip.Chip



class AddGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_game)

        populateSpinner()
    }

    fun addGame(view: View) {
        val repo = GameRepository(this)

        val gameNameEditText: EditText = findViewById(R.id.editTextGameName)
        val genreSpinner: Spinner = findViewById(R.id.spinner)
        val multiplayerChip: SwitchCompat = findViewById(R.id.chip4)

        val gameName = gameNameEditText.text.toString()
        val genre = Genre.valueOf(genreSpinner.selectedItem.toString())
        val multiplayer = multiplayerChip.isChecked

        repo.add(Game(0, gameName, genre, multiplayer))
    }

    private fun populateSpinner() {
        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            Genre.values()
        )
    }
}