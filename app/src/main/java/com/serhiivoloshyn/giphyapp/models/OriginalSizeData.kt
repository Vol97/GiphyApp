package com.serhiivoloshyn.giphyapp.models

import com.google.gson.annotations.SerializedName

data class OriginalSizeData(
    @SerializedName("height")
    var height: String,
    @SerializedName("width")
    var width: String,
    @SerializedName("url")
    var url: String
)