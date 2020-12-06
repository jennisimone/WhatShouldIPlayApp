package com.example.whatshouldiplay.activity.select

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.whatshouldiplay.R

class GameNameView : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_name_view)

        val gameName = intent.getStringExtra(GAME)

        findViewById<TextView>(R.id.gameName).apply {
            text = gameName
        }
    }
}