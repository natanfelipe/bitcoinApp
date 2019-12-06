package com.br.natanfelipe.bitcoinapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.natanfelipe.bitcoinapp.R
import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.dao.BitcoinDao
import com.br.natanfelipe.bitcoinapp.di.component.DaggerApiServiceComponent
import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import com.br.natanfelipe.bitcoinapp.repository.BlockChainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class BlockChainViewModel(private val dao: BitcoinDao) : BaseViewModel() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var repository: BlockChainRepository

    val isLoading by lazy { MutableLiveData<Boolean>() }
    val isToShowMessage by lazy { MutableLiveData<Boolean>() }
    val stats by lazy { MutableLiveData<Bitcoin>() }
    val message by lazy { MutableLiveData<Int>() }

    private fun inject() {
        DaggerApiServiceComponent.builder().build().inject(this)
    }

    init {
        inject()
    }

    fun loadData(isConnected: Boolean): LiveData<Bitcoin> {
        isLoading.value = true
        isToShowMessage.value = false

        launch {
            if (isConnected) {
                val data = repository.fetchApiData(apiService)
                if (data.isSuccessful) {
                    isLoading.value = false
                    stats.value = data.body()
                    data.body()?.let {
                        dao.addValuesData(it.values)
                    }
                    Log.i("DB", "Number of items registered ${dao.getValues().size}")

                } else {
                    isLoading.value = false
                    message.value = R.string.generic_error
                }
            } else {
                isToShowMessage.value = true
                isLoading.value = false
                message.value = R.string.msg_offline
            }
        }

        return stats
    }

}