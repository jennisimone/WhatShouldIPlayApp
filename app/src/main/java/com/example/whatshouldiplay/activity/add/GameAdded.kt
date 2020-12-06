package com.example.whatshouldiplay.activity.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.activity.select.GAME

class GameAdded : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_added)

        val gameName = intent.getStringExtra(GAME)

        findViewById<TextView>(R.id.gameName).apply {
            text = "$gameName has been added, add another?"
        }
    }

    fun addGame(view: View) {
        val intent = Intent(this, AddGame::class.java).apply {
        }
        startActivity(intent)
    }
}