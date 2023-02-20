package com.jennisimone.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.library.LibrarySelection
import com.jennisimone.whatshouldiplay.repository.GenreRepository

class AddGenre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_genre)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.menu.getItem(3).isChecked = true
    }

    fun addGenre(view: View) {
        val genreRepository = GenreRepository(this)
        genreRepository.add(findViewById<EditText>(R.id.editTextGenreName).text.toString())
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