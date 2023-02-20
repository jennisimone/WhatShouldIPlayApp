package com.jennisimone.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.library.LibrarySelection

class Configuration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.menu.getItem(3).isChecked = true
    }

    fun addGenre(view: View) {
        val intent = Intent(this, AddGenre::class.java).apply {
        }
        startActivity(intent)
    }

    fun addPlatform(view: View) {
        val intent = Intent(this, AddPlatform::class.java).apply {
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