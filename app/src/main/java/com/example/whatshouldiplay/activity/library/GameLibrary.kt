package com.example.whatshouldiplay.activity.library

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.activity.select.GAME
import com.example.whatshouldiplay.repository.GameRepository


class GameLibrary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_library)
        populateRows()
    }

    private fun getGameInfo(gameName: String) {
        val intent = Intent(this, GameDetail::class.java).apply {
            putExtra(GAME, gameName)
        }
        startActivity(intent)
    }

    private fun populateRows() {
        val gameRepository = GameRepository(this)
        val gameNames = gameRepository.getAllGames().map { game -> game.name }
        for (index in gameNames.indices) {
            val gameListTable = findViewById<TableLayout>(R.id.GameList)
            val row = TableRow(this)
            val nameOfGame = TextView(this)
            nameOfGame.text = gameNames[index]
            nameOfGame.textSize = 22.0F
            nameOfGame.tag = row.id
            nameOfGame.setTextColor(Color.WHITE)
            row.addView(nameOfGame)
            row.isClickable = true
            row.setOnClickListener { getGameInfo(gameNames[index]); nameOfGame.setTextColor(Color.parseColor("#FFF79385")) }
            gameListTable.addView(
                row,
                TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)
            )
        }
    }
}
