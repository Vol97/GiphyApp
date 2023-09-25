package com.serhiivoloshyn.giphyapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.serhiivoloshyn.giphyapp.database.dao.GeneralGifDataDao
import com.serhiivoloshyn.giphyapp.database.dao.GeneralGifDataRemoteKeysDao
import com.serhiivoloshyn.giphyapp.models.GeneralGifData
import com.serhiivoloshyn.giphyapp.models.GeneralGifDataRemoteKeys

@Database(
    version = 1,
    entities = [GeneralGifData::class, GeneralGifDataRemoteKeys::class]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun generalGifDataDao(): GeneralGifDataDao
    abstract fun generalGifDataRemoteKeysDao(): GeneralGifDataRemoteKeysDao
}