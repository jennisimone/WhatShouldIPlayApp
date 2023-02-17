package com.example.whatshouldiplay.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.whatshouldiplay.database.GenreContract.GenreEntry
import com.example.whatshouldiplay.database.SQLiteDBHelper

class GenreRepository(context: Context) {
    private val dbHelper = SQLiteDBHelper(context)

    fun add(genre: String): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(GenreEntry.COLUMN_NAME_GENRE, genre)
            put(GenreEntry.COLUMN_NAME_BUILTIN, 0)
        }

        return db?.insert(GenreEntry.TABLE_NAME, null, values)
    }

    fun getAllGenres(): List<String> {
        val db = dbHelper.readableDatabase
        val sortOrder = "${GenreEntry.COLUMN_NAME_GENRE} asc"

        val cursor = db.query(
            GenreEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        return convertToPlatform(cursor)
    }


    fun deletePlatform(genreName: Array<String>) {
        val db = dbHelper.writableDatabase
        val selection = "${GenreEntry.COLUMN_NAME_GENRE} =?"

        db.delete(
            GenreEntry.TABLE_NAME,
            selection,
            genreName
        )
    }

    private fun convertToPlatform(cursor: Cursor): ArrayList<String> {
        val allGenres: ArrayList<String> = ArrayList()

        with(cursor) {
            while (moveToNext()) {
                val genreIndex =
                    getColumnIndexOrThrow(GenreEntry.COLUMN_NAME_GENRE)

                val genre =
                    cursor.getString(genreIndex)
                allGenres.add(genre)

            }
        }
        return allGenres
    }
}