package com.serhiivoloshyn.giphyapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "general_gif_data_remote_keys_table")
data class GeneralGifDataRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
