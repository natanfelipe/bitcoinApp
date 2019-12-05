package com.br.natanfelipe.bitcoinapp.connection

import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlockChainApi {

    @GET("charts/market-price")
    suspend fun getStats(
        @Query(value = "timespan", encoded = true) timespan: String,
        @Query("format") format: String
    ): Response<Bitcoin>
}