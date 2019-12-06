package com.br.natanfelipe.bitcoinapp

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.br.natanfelipe.bitcoinapp.dao.BitcoinDatabase
import com.br.natanfelipe.bitcoinapp.viewmodel.BlockChainViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlockChainViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, BitcoinDatabase::class.java, "bitcoin_db").build()
            @Suppress("UNCHECKED_CAST")
            return BlockChainViewModel(db.bitcoinDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}