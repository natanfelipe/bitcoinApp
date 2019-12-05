package com.br.natanfelipe.bitcoinapp.di.component

import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.di.module.ApiServiceModule
import com.br.natanfelipe.bitcoinapp.di.module.RoomModule
import com.br.natanfelipe.bitcoinapp.di.module.UtilsModule
import com.br.natanfelipe.bitcoinapp.ui.MainActivity
import com.br.natanfelipe.bitcoinapp.viewmodel.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiServiceModule::class, UtilsModule::class, RoomModule::class])
interface AppComponent {

    fun inject(baseViewModel: BaseViewModel)
    fun inject(apiservice: ApiService)
    fun inject(mainActivity: MainActivity)

}