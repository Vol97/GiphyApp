package com.serhiivoloshyn.giphyapp.di

import com.serhiivoloshyn.giphyapp.ui.screens.gifSearchScreen.GiphyRepository
import com.serhiivoloshyn.giphyapp.ui.screens.gifSearchScreen.GiphyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GiphyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindGiphyRepository(
        giphyRepositoryImpl: GiphyRepositoryImpl
    ): GiphyRepository
}