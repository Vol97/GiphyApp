package com.serhiivoloshyn.giphyapp.models

import com.google.gson.annotations.SerializedName


data class DownsizedSizeData(
    @SerializedName("height")
    var downsizedHeight: String,
    @SerializedName("width")
    var downsizedWidth: String,
    @SerializedName("url")
    var downsizedUrl: String
)