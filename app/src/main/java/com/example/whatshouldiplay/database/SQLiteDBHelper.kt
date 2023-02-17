package com.example.whatshouldiplay.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.whatshouldiplay.database.GameContract.GameEntry
import com.example.whatshouldiplay.database.GenreContract.GenreEntry
import com.example.whatshouldiplay.database.PlatformContract.PlatformEntry

private const val SQL_CREATE_GAMES_ENTRIES =
    "CREATE TABLE ${GameEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${GameEntry.COLUMN_NAME_TITLE} TEXT," +
            "${GameEntry.COLUMN_NAME_GENRE} TEXT," +
            "${GameEntry.COLUMN_NAME_PLATFORM} TEXT," +
            "${GameEntry.COLUMN_NAME_MULTIPLAYER} INT)"

private const val SQL_CREATE_PLATFORM_ENTRIES =
    "CREATE TABLE ${PlatformEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${PlatformEntry.COLUMN_NAME_PLATFORM} TEXT," +
            "${PlatformEntry.COLUMN_NAME_BUILTIN} INTEGER DEFAULT 0)"

private const val SQL_INSERT_PLATFORM_DEFAULTS =
    "INSERT INTO ${PlatformEntry.TABLE_NAME} (${PlatformEntry.COLUMN_NAME_PLATFORM}, ${PlatformEntry.COLUMN_NAME_BUILTIN})" +
            "VALUES " +
            "('MOBILE', '1')," +
            "('PC', '1')," +
            "('PS4', '1')," +
            "('PS5', '1')," +
            "('SWITCH', '1')," +
            "('XBOX_ONE', '1')," +
            "('XBOX_SERIES_X_S', '1')"

private const val SQL_CREATE_GENRE_ENTRIES =
    "CREATE TABLE ${GenreEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${GenreEntry.COLUMN_NAME_GENRE} TEXT," +
            "${GenreEntry.COLUMN_NAME_BUILTIN} INTEGER DEFAULT 0)"

private const val SQL_INSERT_GENRE_DEFAULTS =
    "INSERT INTO ${GenreEntry.TABLE_NAME} (${GenreEntry.COLUMN_NAME_GENRE}, ${GenreEntry.COLUMN_NAME_BUILTIN})" +
            "VALUES " +
            "('ADVENTURE', '1')," +
            "('HORROR', '1')," +
            "('PARTY', '1')," +
            "('RACING', '1')," +
            "('RPG', '1')," +
            "('PLATFORM', '1')," +
            "('PUZZLE', '1')," +
            "('SIMULATION', '1')," +
            "('SHOOTER', '1')," +
            "('SPORT', '1')"

private const val SQL_DELETE_GAME_ENTRIES = "DROP TABLE IF EXISTS ${GameEntry.TABLE_NAME}"
private const val SQL_DELETE_PLATFORM_ENTRIES = "DROP TABLE IF EXISTS ${PlatformEntry.TABLE_NAME}"
private const val SQL_DELETE_GENRE_ENTRIES = "DROP TABLE IF EXISTS ${GenreEntry.TABLE_NAME}"

class SQLiteDBHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_GAMES_ENTRIES)
        db.execSQL(SQL_CREATE_PLATFORM_ENTRIES)
        db.execSQL(SQL_INSERT_PLATFORM_DEFAULTS)
        db.execSQL(SQL_CREATE_GENRE_ENTRIES)
        db.execSQL(SQL_INSERT_GENRE_DEFAULTS)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_GAME_ENTRIES);
        db.execSQL(SQL_DELETE_PLATFORM_ENTRIES);
        db.execSQL(SQL_DELETE_GENRE_ENTRIES);
        onCreate(db);
    }

    companion object {
        private const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "what_should_i_play"
    }

}