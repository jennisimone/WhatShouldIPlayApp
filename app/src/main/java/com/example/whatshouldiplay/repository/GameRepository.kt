package com.example.whatshouldiplay.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import com.example.whatshouldiplay.database.GameContract.GameEntry
import com.example.whatshouldiplay.database.SQLiteDBHelper
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre
import com.example.whatshouldiplay.domain.Platform

class GameRepository(context: Context) {
    private val dbHelper = SQLiteDBHelper(context)

    fun add(game: Game): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(GameEntry.COLUMN_NAME_TITLE, game.name)
            put(GameEntry.COLUMN_NAME_GENRE, game.genre.name)
            put(GameEntry.COLUMN_NAME_PLATFORM, game.platform.name)
            put(GameEntry.COLUMN_NAME_MULTIPLAYER, game.multiPlayer)
        }

        return db?.insert(GameEntry.TABLE_NAME, null, values)
    }

    fun getAllGames(): List<Game> {
        val db = dbHelper.readableDatabase
        val sortOrder = "${GameEntry.COLUMN_NAME_TITLE} asc"

        val cursor = db.query(
            GameEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        return convertToGame(cursor)
    }

    fun getFilteredGames(multiplayer: Boolean, genre: Array<String>, platform: Array<String>): List<Game> {
        val allGames = getAllGames()
        if(multiplayer) {
            allGames.filter { game -> game.multiPlayer == multiplayer }
        }
        if (genre.isNotEmpty()) {
            allGames.filter { game -> genre.contains(game.genre.name) }
        }
        if (platform.isNotEmpty()) {
            allGames.filter { game -> platform.contains(game.platform.name) }
        }
        return allGames
    }

    fun getGame(name: String): Game {
        val db = dbHelper.readableDatabase
        val selection = "${GameEntry.COLUMN_NAME_TITLE} =?"

        val cursor = db.query(
            GameEntry.TABLE_NAME,
            null,
            selection,
            arrayOf(name),
            null,
            null,
            null
        )
        return convertToGame(cursor)[0]
    }

    fun deleteGame(gameName: Array<String>) {
        val db = dbHelper.writableDatabase
        val selection = "${GameEntry.COLUMN_NAME_TITLE} =?"

        db.delete(
            GameEntry.TABLE_NAME,
            selection,
            gameName
        )
    }

    fun amend(game: Game) {
        val db = dbHelper.writableDatabase
        val selection = "$_ID =?"
        val values = ContentValues().apply {
            put(GameEntry.COLUMN_NAME_TITLE, game.name)
            put(GameEntry.COLUMN_NAME_GENRE, game.genre.name)
            put(GameEntry.COLUMN_NAME_PLATFORM, game.platform.name)
            put(GameEntry.COLUMN_NAME_MULTIPLAYER, game.multiPlayer)
        }
        
        db.update(
            GameEntry.TABLE_NAME,
            values,
            selection,
            arrayOf(game.id.toString())
        )

    }

    private fun convertToGame(cursor: Cursor): ArrayList<Game> {
        val allGames: ArrayList<Game> = ArrayList()

        with(cursor) {
            while (moveToNext()) {
                val idIndex = getColumnIndexOrThrow(BaseColumns._ID)
                val nameIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_TITLE)
                val genreIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_GENRE)
                val platformIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_PLATFORM)
                val multiplayerIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_MULTIPLAYER)

                val game = Game(
                    cursor.getLong(idIndex),
                    cursor.getString(nameIndex),
                    Genre.valueOf(cursor.getString(genreIndex)),
                    Platform.valueOf(cursor.getString(platformIndex)),
                    intToBoolean(cursor.getInt(multiplayerIndex))
                )
                allGames.add(game)
            }
        }

        return allGames
    }

    private fun intToBoolean(value: Int): Boolean {
        return value == 1
    }

}