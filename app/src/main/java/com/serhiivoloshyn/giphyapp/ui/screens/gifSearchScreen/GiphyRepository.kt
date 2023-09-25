package com.serhiivoloshyn.giphyapp.ui.screens.gifSearchScreen

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.serhiivoloshyn.giphyapp.api.GiphyApi
import com.serhiivoloshyn.giphyapp.database.AppDatabase
import com.serhiivoloshyn.giphyapp.models.GeneralGifData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GiphyRepository {
    suspend fun getSearchGifs(
        searchString: String
    ): Flow<PagingData<GeneralGifData>>
}

class GiphyRepositoryImpl @Inject constructor(
    private val api: GiphyApi,
    private val database: AppDatabase
) : GiphyRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getSearchGifs(
        searchString: String
    ): Flow<PagingData<GeneralGifData>> {
        val pagingSourceFactory = { database.generalGifDataDao().getAllGifs() }

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 20,
                prefetchDistance = 5
            ),
            remoteMediator = GiphyRemoteMediator(
                api = api,
                database = database,
                searchString = searchString
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

}