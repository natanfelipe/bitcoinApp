package com.br.natanfelipe.bitcoinapp.repository

import com.br.natanfelipe.bitcoinapp.connection.ApiService
import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import retrofit2.Response

class BlockChainRepository {

    suspend fun fetchApiData(apiService: ApiService): Response<Bitcoin> = apiService.getStats()

}