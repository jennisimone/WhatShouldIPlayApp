package com.jennisimone.whatshouldiplay.database

import android.provider.BaseColumns

class GenreContract {
    object GenreEntry: BaseColumns {
        const val TABLE_NAME = "genres"
        const val COLUMN_NAME_GENRE = "genre"
        const val COLUMN_NAME_BUILTIN = "builtin"
    }
}