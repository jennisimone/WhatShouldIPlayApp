package com.example.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.repository.GenreRepository

class AddGenre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_genre)
    }

    fun addGenre(view: View) {
        val genreRepository = GenreRepository(this)
        genreRepository.add(findViewById<EditText>(R.id.editTextGenreName).text.toString())
        val intent = Intent(this, Configuration::class.java).apply {
        }
        startActivity(intent)
    }
}