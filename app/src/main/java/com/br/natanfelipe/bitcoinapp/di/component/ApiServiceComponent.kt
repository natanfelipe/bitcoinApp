package com.br.natanfelipe.bitcoinapp.di.component

import android.app.Application
import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.di.module.ApiServiceModule
import com.br.natanfelipe.bitcoinapp.di.module.UtilsModule
import com.br.natanfelipe.bitcoinapp.ui.MainActivity
import com.br.natanfelipe.bitcoinapp.viewmodel.BlockChainViewModel
import dagger.Component

@Component(modules = [ApiServiceModule::class, UtilsModule::class])
interface ApiServiceComponent {

    fun inject(blockChainViewModel: BlockChainViewModel)
    fun inject(apiservice: ApiService)
    fun inject(mainActivity: MainActivity)
    fun inject(application: Application)

}