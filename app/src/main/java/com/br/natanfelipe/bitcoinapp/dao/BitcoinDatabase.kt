package com.br.natanfelipe.bitcoinapp.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br.natanfelipe.bitcoinapp.model.Bitcoin
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Bitcoin::class], version = 1, exportSchema = false)
abstract class BitcoinDatabase : RoomDatabase() {

    abstract fun bitcoinDao(): BitcoinDao

    companion object {
        @Volatile
        private var INSTANCE: BitcoinDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BitcoinDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BitcoinDatabase::class.java,
                    "bitcoin_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
