package com.serhiivoloshyn.giphyapp.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.serhiivoloshyn.giphyapp.models.GeneralGifData

@Dao
interface GeneralGifDataDao {

    @Query("SELECT * FROM general_gif_data_table")
    fun getAllGifs(): PagingSource<Int, GeneralGifData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGifs(images: List<GeneralGifData>)

    @Query("DELETE FROM general_gif_data_table")
    suspend fun deleteAllGifs()

    @Query("DELETE FROM general_gif_data_table WHERE id = :id")
    suspend fun deleteGif(id: String)
}