package com.example.whatshouldiplay.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.whatshouldiplay.database.GameContract.GameEntry.COLUMN_NAME_GENRE
import com.example.whatshouldiplay.database.GameContract.GameEntry.COLUMN_NAME_MULTIPLAYER
import com.example.whatshouldiplay.database.GameContract.GameEntry.COLUMN_NAME_PLATFORM
import com.example.whatshouldiplay.database.GameContract.GameEntry.COLUMN_NAME_TITLE
import com.example.whatshouldiplay.database.GameContract.GameEntry.TABLE_NAME

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "$COLUMN_NAME_TITLE TEXT," +
            "$COLUMN_NAME_GENRE TEXT," +
            "$COLUMN_NAME_PLATFORM TEXT," +
            "$COLUMN_NAME_MULTIPLAYER INT UNSIGNED)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"

class SQLiteDBHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    companion object {
        private const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "what_should_i_play"
    }

}