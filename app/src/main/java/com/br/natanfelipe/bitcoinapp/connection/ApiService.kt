package com.br.natanfelipe.bitcoinapp.connection

import com.br.natanfelipe.bitcoinapp.di.component.DaggerAppComponent
import javax.inject.Inject

class ApiService {

    @Inject
    lateinit var api: BlockChainApi

    init {
        DaggerAppComponent
            .create()
            .inject(this)
    }

    suspend fun getStats() = api.getStats("7days", "json")


}