package com.br.natanfelipe.bitcoinapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.natanfelipe.bitcoinapp.dao.BitcoinDatabase
import com.br.natanfelipe.bitcoinapp.viewmodel.BlockChainViewModel


class BitcoinViewModelFactory(val bitcoinDatabase: BitcoinDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BlockChainViewModel(bitcoinDatabase) as T
    }
}