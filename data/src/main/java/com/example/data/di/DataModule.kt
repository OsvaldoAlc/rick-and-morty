package com.example.data.di

import com.example.data.repository.CharacterRepository
import com.example.data.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {


    @Binds
    fun bindsUserDataRepository(
        userDataRepository: NetworkRepository,
    ): CharacterRepository


}