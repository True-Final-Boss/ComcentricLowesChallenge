package com.example.comcentriclowes.di

import com.example.comcentriclowes.rest.WeatherRepository
import com.example.comcentriclowes.rest.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideWeatherRepo(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}