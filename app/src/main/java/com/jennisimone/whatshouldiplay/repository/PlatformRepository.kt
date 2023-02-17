package com.jennisimone.whatshouldiplay.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.jennisimone.whatshouldiplay.database.PlatformContract
import com.jennisimone.whatshouldiplay.database.SQLiteDBHelper

class PlatformRepository(context: Context) {
    private val dbHelper = SQLiteDBHelper(context)

    fun add(platform: String): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(PlatformContract.PlatformEntry.COLUMN_NAME_PLATFORM, platform.uppercase())
            put(PlatformContract.PlatformEntry.COLUMN_NAME_BUILTIN, 0)
        }

        return db?.insert(PlatformContract.PlatformEntry.TABLE_NAME, null, values)
    }

    fun getAllPlatforms(): List<String> {
        val db = dbHelper.readableDatabase
        val sortOrder = "${PlatformContract.PlatformEntry.COLUMN_NAME_PLATFORM} asc"

        val cursor = db.query(
            PlatformContract.PlatformEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            sortOrder
        )

        return convertToPlatform(cursor)
    }


    fun deletePlatform(platformName: Array<String>) {
        val db = dbHelper.writableDatabase
        val selection = "${PlatformContract.PlatformEntry.COLUMN_NAME_PLATFORM} =?"

        db.delete(
            PlatformContract.PlatformEntry.TABLE_NAME,
            selection,
            platformName
        )
    }

    private fun convertToPlatform(cursor: Cursor): ArrayList<String> {
        val allPlatforms: ArrayList<String> = ArrayList()

        with(cursor) {
            while (moveToNext()) {
                val platformIndex =
                    getColumnIndexOrThrow(PlatformContract.PlatformEntry.COLUMN_NAME_PLATFORM)

                val platform =
                    cursor.getString(platformIndex)
                allPlatforms.add(platform)

            }
        }
        return allPlatforms
    }
}