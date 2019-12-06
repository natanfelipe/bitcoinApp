package com.br.natanfelipe.bitcoinapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.br.natanfelipe.bitcoinapp.model.Values

@Dao
interface BitcoinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addValuesData(values: List<Values>)

    @Query("SELECT * FROM `Values`")
    suspend fun getValues(): List<Values>

}