package com.darrylbayliss.carelist.helpers

import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkRequest

class ConnectivityChecker(private val connectivityManager: ConnectivityManager) {

    var isConnectedToNetwork: Boolean = false

    lateinit var connectedToNetworkListener: (Boolean) -> Unit

    fun beginScanning() {
        val networkRequest = NetworkRequest.Builder()
            .build()

        val networkCallback: NetworkCallback = object : NetworkCallback() {
            override fun onAvailable(network: Network) {
                connectedToNetworkListener(true)
                isConnectedToNetwork = true
            }

            override fun onLost(network: Network) {
                connectedToNetworkListener(false)
                isConnectedToNetwork = false
            }
        }

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}