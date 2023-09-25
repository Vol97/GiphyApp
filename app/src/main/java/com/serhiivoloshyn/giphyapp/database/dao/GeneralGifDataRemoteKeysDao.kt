package com.serhiivoloshyn.giphyapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serhiivoloshyn.giphyapp.models.GeneralGifDataRemoteKeys

@Dao
interface GeneralGifDataRemoteKeysDao {

    @Query("SELECT * FROM general_gif_data_remote_keys_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): GeneralGifDataRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<GeneralGifDataRemoteKeys>)

    @Query("DELETE FROM general_gif_data_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}