package com.example.whatshouldiplay.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.whatshouldiplay.database.GameContract.GameEntry
import com.example.whatshouldiplay.database.SQLiteDBHelper
import com.example.whatshouldiplay.domain.Game
import com.example.whatshouldiplay.domain.Genre

class GameRepository(context: Context) {
    private val dbHelper = SQLiteDBHelper(context)

    fun add(game: Game): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(GameEntry.COLUMN_NAME_TITLE, game.name)
            put(GameEntry.COLUMN_NAME_GENRE, game.genre.name)
            put(GameEntry.COLUMN_NAME_MULTIPLAYER, game.multiPlayer)
        }

        return db?.insert(GameEntry.TABLE_NAME, null, values)
    }

    fun getAllGames(): ArrayList<Game> {
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

    fun getMultiPlayerGames(): ArrayList<Game> {
        val db = dbHelper.readableDatabase
        val selection = "${GameEntry.COLUMN_NAME_MULTIPLAYER} = ?"
        val selectionArgs = arrayOf("1")
        val sortOrder = "${GameEntry.COLUMN_NAME_TITLE} asc"

        val cursor = db.query(
            GameEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        return convertToGame(cursor)
    }

    fun getGamesByGenre(genre: Array<String>): ArrayList<Game> {
        val db = dbHelper.readableDatabase
        val selection = "${GameEntry.COLUMN_NAME_GENRE} = ?"
        val sortOrder = "${GameEntry.COLUMN_NAME_TITLE} asc"

        val cursor = db.query(
            GameEntry.TABLE_NAME,
            null,
            selection,
            genre,
            null,
            null,
            sortOrder
        )
        return convertToGame(cursor)
    }

    private fun convertToGame(cursor: Cursor): ArrayList<Game> {
        val allGames: ArrayList<Game> = ArrayList()

        with(cursor) {
            while (moveToNext()) {
                val idIndex = getColumnIndexOrThrow(BaseColumns._ID)
                val nameIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_TITLE)
                val genreIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_GENRE)
                val multiplayerIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_MULTIPLAYER)

                val game = Game(
                    cursor.getLong(idIndex),
                    cursor.getString(nameIndex),
                    Genre.valueOf(cursor.getString(genreIndex)),
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