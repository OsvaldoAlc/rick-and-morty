package com.example.network.di

import com.example.network.retrofit.RetrofitRmNetwork
import com.example.network.retrofit.RmNetworkDataSource
import dagger.Binds

interface NetworkModuleBinds {
    @Binds
    fun binds(impl: RetrofitRmNetwork): RmNetworkDataSource
}