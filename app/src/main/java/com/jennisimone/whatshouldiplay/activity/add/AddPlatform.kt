package com.jennisimone.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jennisimone.whatshouldiplay.R
import com.jennisimone.whatshouldiplay.activity.MainActivity
import com.jennisimone.whatshouldiplay.activity.library.LibrarySelection
import com.jennisimone.whatshouldiplay.repository.PlatformRepository

class AddPlatform : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_platform)
    }

    fun addPlatform(view: View) {
        val platformRepository = PlatformRepository(this)
        platformRepository.add(findViewById<EditText>(R.id.editTextPlatformName).text.toString())
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