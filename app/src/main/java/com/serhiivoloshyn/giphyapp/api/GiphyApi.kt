package com.serhiivoloshyn.giphyapp.api

import com.serhiivoloshyn.giphyapp.models.GifsResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("search?api_key=Sot1MHEeje1yB8UP7XdItLzNWQhky6a9")
    suspend fun getSearchGifs(
        @Query("q") item: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String = "g",
        @Query("lang") lang: String = "en"
    ): Response<GifsResponseModel>
}