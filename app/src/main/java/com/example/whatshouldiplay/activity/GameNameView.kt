package com.example.whatshouldiplay.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.whatshouldiplay.R

class GameNameView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_name_view)

        val gameName = intent.getStringExtra(GAME)

        val textView = findViewById<TextView>(R.id.textView).apply {
            text = gameName
        }
    }
}