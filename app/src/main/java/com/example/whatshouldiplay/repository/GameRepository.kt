package com.example.whatshouldiplay.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
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
        return getAllGames().filter {game ->
                game.multiPlayer == multiplayer
                genre.contains(game.genre.name)
                platform.contains(game.platform.name)
        }
    }

    fun deleteGames(gameName: Array<String>) {
        val db = dbHelper.writableDatabase
        val selection = "${GameEntry.COLUMN_NAME_TITLE} =?"

        db.delete(
            GameEntry.TABLE_NAME,
            selection,
            gameName
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