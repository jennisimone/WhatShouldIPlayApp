package com.jennisimone.whatshouldiplay.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.add.AddGame
import com.jennisimone.whatshouldiplay.activity.add.Configuration
import com.jennisimone.whatshouldiplay.activity.library.LibrarySelection
import com.jennisimone.whatshouldiplay.activity.select.GameSelection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        val homeItem = bottomNav.menu.getItem(1)
        homeItem.isChecked = true
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

    fun configure(view: View) {
        val intent = Intent(this, Configuration::class.java).apply {
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