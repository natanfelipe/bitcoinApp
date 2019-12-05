package com.br.natanfelipe.bitcoinapp.di.module

import android.app.Application
import android.content.Context
import com.br.natanfelipe.bitcoinapp.dao.BitcoinDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
class RoomModule{

    @Provides
    @Singleton
    fun providesDatabase(application: Application, scope: CoroutineScope) = BitcoinDatabase.getDatabase(application, scope)

    @Provides
    @Singleton
    fun providesDevicesDao(bitcoinDatabase: BitcoinDatabase) = bitcoinDatabase.bitcoinDao()

}