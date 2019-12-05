package com.br.natanfelipe.bitcoinapp.viewmodel

import androidx.lifecycle.ViewModel
import com.br.natanfelipe.bitcoinapp.di.component.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun inject(){
        DaggerAppComponent.builder()
            .build()
            .inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}