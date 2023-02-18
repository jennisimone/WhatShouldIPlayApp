package com.jennisimone.whatshouldiplay.activity.library

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.add.AddGame

class LibrarySelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_selection)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val libraryItem = bottomNav.menu.getItem(0)
        libraryItem.isChecked = true
    }

    fun allGames(view: View) {
        val intent = Intent(this, GameLibrary::class.java).apply {
        }
        startActivity(intent)
    }

    fun filteredGames(view: View) {
        val intent = Intent(this, GameFilter::class.java).apply {
        }
        startActivity(intent)
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