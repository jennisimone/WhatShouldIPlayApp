package com.example.whatshouldiplay.activity.add

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.whatshouldiplay.R
import com.example.whatshouldiplay.repository.PlatformRepository

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
}