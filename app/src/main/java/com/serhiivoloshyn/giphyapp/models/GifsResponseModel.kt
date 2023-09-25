package com.serhiivoloshyn.giphyapp.models

import com.google.gson.annotations.SerializedName
import com.serhiivoloshyn.giphyapp.models.GeneralGifData

data class GifsResponseModel(
    @SerializedName("data")
    val list: List<GeneralGifData>
)