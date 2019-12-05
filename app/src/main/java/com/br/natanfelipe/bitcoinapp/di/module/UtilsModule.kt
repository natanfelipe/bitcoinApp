package com.br.natanfelipe.bitcoinapp.di.module

import com.br.natanfelipe.bitcoinapp.utils.Utils
import dagger.Module
import dagger.Provides

@Module
open class UtilsModule {

    @Provides
    fun provideUtils() = Utils()
}