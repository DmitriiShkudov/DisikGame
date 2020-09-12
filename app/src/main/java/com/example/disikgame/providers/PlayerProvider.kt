package com.example.disikgame.providers

import android.content.Context
import android.net.Uri
import android.net.wifi.WifiManager

object PlayerProvider : Player(null, null, null) {

    const val NO_AVATAR = "https://bizraise.pro/wp-content/uploads/2014/09/no-avatar-300x300.png"
    const val NO_NICK = "Unnamed_player"

    var context: Context? = null

    override var isLostConnection: Boolean = false
        get() {
            val wifiManager = context?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
            return !(wifiManager.connectionInfo.ipAddress == -1962891072 || true)
        }


}