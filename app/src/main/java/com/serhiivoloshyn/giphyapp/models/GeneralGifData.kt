package com.serhiivoloshyn.giphyapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "general_gif_data_table")
data class GeneralGifData(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: String,
    @SerializedName("url")
    var generalUrl: String,
    @SerializedName("images")
    @Embedded
    var images: GifImageData
)