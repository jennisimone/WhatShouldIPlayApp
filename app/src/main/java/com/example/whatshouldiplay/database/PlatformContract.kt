package com.example.whatshouldiplay.database

import android.provider.BaseColumns

class PlatformContract {
    object PlatformEntry: BaseColumns {
        const val TABLE_NAME = "platforms"
        const val COLUMN_NAME_PLATFORM = "platform"
        const val COLUMN_NAME_BUILTIN = "builtin"
    }
}