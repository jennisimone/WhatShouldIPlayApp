package com.jennisimone.whatshouldiplay.activity.library

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.add.AddGame
import com.jennisimone.whatshouldiplay.activity.select.GAME
import com.jennisimone.whatshouldiplay.repository.GameRepository

class GameLibrary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_library)
        populateRows()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.menu.getItem(3).isChecked = true
    }

    private fun getGameInfo(gameName: String) {
        val intent = Intent(this, GameDetail::class.java).apply {
            putExtra(GAME, gameName)
        }
        startActivity(intent)
    }

    private fun populateRows() {
        var gameNames = getAvailableGames()
        val noGameFound = "No games found ☹️"

        if (gameNames.isEmpty()) {
            gameNames = listOf(noGameFound)
        }

        for (index in gameNames.indices) {
            val gameListTable = findViewById<TableLayout>(R.id.GameList)
            val row = TableRow(this)
            val nameOfGame = TextView(this)

            nameOfGame.text = gameNames[index]
            nameOfGame.textSize = 22.0F
            nameOfGame.tag = row.id
            nameOfGame.setTextColor(Color.WHITE)
            nameOfGame.setTextAppearance(R.style.LibraryTheme);

            row.addView(nameOfGame)

            if (nameOfGame.text != noGameFound) {
                row.isClickable = true
                row.setOnClickListener { getGameInfo(gameNames[index]); nameOfGame.setTextColor(Color.parseColor("#FFF79385")) }
            }

            gameListTable.addView(
                row,
                TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)
            )
        }
    }

    private fun getAvailableGames(): List<String> {
        val gameRepository = GameRepository(this)

        val filtered = intent.getBooleanExtra("filtered", false);

        return if (filtered) {
            val completed = intent.getBooleanExtra("completed", false)
            val multiplayer = intent.getBooleanExtra("multiplayer", false)
            val genre = intent.getStringArrayExtra("genre")!!.copyOf()
            val platform = intent.getStringArrayExtra("platform")!!.copyOf()

            gameRepository.getFilteredGames(completed, multiplayer, genre, platform).map { game -> game.name }
        } else {
            gameRepository.getAllGames().map { game -> game.name }
        }
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
