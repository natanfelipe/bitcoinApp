package com.br.natanfelipe.bitcoinapp.utils

import android.content.Context
import android.net.ConnectivityManager

class Utils {
    fun isConnectedToInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetworkInfo
            isConnected = activeNetwork != null && activeNetwork.isConnected
        }

        return  isConnected
    }
}