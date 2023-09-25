package com.serhiivoloshyn.giphyapp.models

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class GifImageData(
    @SerializedName("original")
    @Embedded
    var original: OriginalSizeData,
    @SerializedName("downsized")
    @Embedded
    var downsized: DownsizedSizeData
)