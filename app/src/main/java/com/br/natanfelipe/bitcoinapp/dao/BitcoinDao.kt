package com.br.natanfelipe.bitcoinapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.natanfelipe.bitcoinapp.model.Bitcoin

@Dao
interface BitcoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBitcoinData(bitcoin: Bitcoin): Bitcoin

    @Query("SELECT * FROM bitcoinInfo")
    suspend fun getBitcoin(): List<Bitcoin>

}