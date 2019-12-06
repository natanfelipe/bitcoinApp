package com.br.natanfelipe.bitcoinapp.connection

import com.br.natanfelipe.bitcoinapp.di.component.DaggerApiServiceComponent
import com.br.natanfelipe.bitcoinapp.di.module.ApiServiceModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ApiService {

    @Inject
    lateinit var api: BlockChainApi

    init {
        DaggerApiServiceComponent
            .create()
            .inject(this)
    }

    suspend fun getStats() = api.getStats("7days", "json")


}