package com.example.network.di

import com.example.network.retrofit.RetrofitRmNetwork
import com.example.network.retrofit.RmNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModuleBinds {
    @Binds
    fun binds(impl: RetrofitRmNetwork): RmNetworkDataSource
}