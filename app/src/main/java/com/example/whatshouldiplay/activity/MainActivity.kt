package com.example.whatshouldiplay.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.activity.add.AddGame
import com.example.whatshouldiplay.activity.library.LibrarySelection
import com.example.whatshouldiplay.activity.select.GameSelection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun chooseGame(view: View) {
        val intent = Intent(this, GameSelection::class.java).apply {
        }
        startActivity(intent)
    }

    fun addGame(view: View) {
        val intent = Intent(this, AddGame::class.java).apply {
        }
        startActivity(intent)
    }

    fun gameLibrary(view: View) {
        val intent = Intent(this, LibrarySelection::class.java).apply {
        }
        startActivity(intent)
    }
}