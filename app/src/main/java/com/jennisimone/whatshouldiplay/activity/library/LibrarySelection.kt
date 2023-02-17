package com.jennisimone.whatshouldiplay.activity.library

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jennisimone.whatshouldiplay.R

class LibrarySelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_selection)
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
}