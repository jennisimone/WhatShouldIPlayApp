package com.jennisimone.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jennisimone.whatshouldiplay.R

class Configuration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
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
}