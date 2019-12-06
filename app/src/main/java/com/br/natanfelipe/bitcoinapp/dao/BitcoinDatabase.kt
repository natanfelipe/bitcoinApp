package com.br.natanfelipe.bitcoinapp.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.br.natanfelipe.bitcoinapp.model.Values

@Database(entities = arrayOf(Values::class), version = 1, exportSchema = false)
abstract class BitcoinDatabase : RoomDatabase() {
    abstract fun bitcoinDao(): BitcoinDao
}