package com.jennisimone.whatshouldiplay.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import com.jennisimone.whatshouldiplay.database.GameContract.GameEntry
import com.jennisimone.whatshouldiplay.database.SQLiteDBHelper
import com.jennisimone.whatshouldiplay.domain.Game

class GameRepository(context: Context) {
    private val dbHelper = SQLiteDBHelper(context)

    fun add(game: Game): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(GameEntry.COLUMN_NAME_TITLE, game.name)
            put(GameEntry.COLUMN_NAME_GENRE, game.genre)
            put(GameEntry.COLUMN_NAME_PLATFORM, game.platform)
            put(GameEntry.COLUMN_NAME_COMPLETED, game.completed)
            put(GameEntry.COLUMN_NAME_MULTIPLAYER, game.multiPlayer)
        }

        return db?.insert(GameEntry.TABLE_NAME, null, values)
    }

    fun getAllGames(shouldIncludeCompleted: Boolean = true): List<Game> {
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

        return if(shouldIncludeCompleted) convertToGame(cursor) else convertToGame(cursor).filter { game -> !game.completed }
    }

    fun getFilteredGames(includeCompleted: Boolean = false, multiplayer: Boolean, genre: Array<String>, platform: Array<String>): List<Game> {
        var allGames = getAllGames()
        if(!includeCompleted) {
            allGames = allGames.filter { game -> game.completed == includeCompleted }
        }
        if(multiplayer) {
            allGames = allGames.filter { game -> game.multiPlayer == multiplayer }
        }
        if (!genre.contains("ALL")) {
            allGames = allGames.filter { game -> genre.contains(game.genre) }
        }
        if (!platform.contains("ALL")) {
            allGames = allGames.filter { game -> platform.contains(game.platform) }
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
            put(GameEntry.COLUMN_NAME_GENRE, game.genre)
            put(GameEntry.COLUMN_NAME_PLATFORM, game.platform)
            put(GameEntry.COLUMN_NAME_COMPLETED, game.completed)
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
                val completedIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_COMPLETED)
                val multiplayerIndex = getColumnIndexOrThrow(GameEntry.COLUMN_NAME_MULTIPLAYER)

                val game = Game(
                    cursor.getLong(idIndex),
                    cursor.getString(nameIndex),
                    cursor.getString(genreIndex),
                    cursor.getString(platformIndex),
                    intToBoolean(cursor.getInt(completedIndex)),
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