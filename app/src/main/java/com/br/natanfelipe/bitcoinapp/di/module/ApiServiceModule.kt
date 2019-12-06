package com.br.natanfelipe.bitcoinapp.di.module

import com.br.natanfelipe.bitcoinapp.BuildConfig
import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.connection.BlockChainApi
import com.br.natanfelipe.bitcoinapp.repository.BlockChainRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApiServiceModule {

    @Provides
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BlockChainApi::class.java)

    @Provides
    fun provideApiService(): ApiService = ApiService()

    @Provides
    fun provideRepository() = BlockChainRepository()

    /*@Provides
    fun provideServiceApi() = BlockChainApi()*/
}