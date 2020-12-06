package com.example.whatshouldiplay.database

import android.provider.BaseColumns

class GameContract {
    object GameEntry: BaseColumns {
        const val TABLE_NAME = "games"
        const val COLUMN_NAME_TITLE = "name"
        const val COLUMN_NAME_GENRE = "genre"
        const val COLUMN_NAME_PLATFORM = "platform"
        const val COLUMN_NAME_MULTIPLAYER = "multiplayer"
    }
}