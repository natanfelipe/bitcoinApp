package com.br.natanfelipe.bitcoinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.br.natanfelipe.bitcoinapp.R
import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.dao.BitcoinDatabase
import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import com.br.natanfelipe.bitcoinapp.repository.BlockChainRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class BlockChainViewModel constructor(bitcoinDatabase: BitcoinDatabase): BaseViewModel() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var repository: BlockChainRepository

    val isLoading by lazy { MutableLiveData<Boolean>() }
    val stats by lazy { MutableLiveData<Bitcoin>() }
    val message by lazy { MutableLiveData<Int>() }

    init {
        inject()
     //   roomDatabase = BitcoinDatabase.getDatabase(application)
    }

    fun loadData(isConnected: Boolean): LiveData<Bitcoin>{
        isLoading.value = true

        //todo add save and retrieve data in room
        //todo work data on repository

        launch {
            if(isConnected){
                val data = repository.fetchApiData(apiService)
                //val bitcoinList = roomDatabase.bitcoinDao().getBitcoin()

                if(data.isSuccessful){
                    isLoading.value = false
                    stats.value = data.body()
                   /* if(bitcoinList.size == 0){
                        roomDatabase.bitcoinDao().addBitcoinData(stats.value!!)
                    } else {
                        Log.d("TESTE", "${bitcoinList[0].name}")
                    }*/
                } else {
                    isLoading.value = false
                }
            } else {
              isLoading.value = false
              message.value = R.string.msg_offline
            }
        }

        return stats
    }

}