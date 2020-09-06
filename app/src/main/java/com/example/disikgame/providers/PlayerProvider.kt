package com.example.disikgame.providers

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.example.disikgame.activities.LobbyActivity
import com.example.disikgame.activities.LobbyActivity.Companion.USER_INFO
import com.example.disikgame.http_client.HttpClient
import com.example.disikgame.models.Player

class PlayerProvider( private val context: Context ) : Player() {


    override val avatarUri: Uri =

        Uri.parse(

        context.
        getSharedPreferences(USER_INFO, 0).
        getString(LobbyActivity.KEY_USER_AVATAR_URI, NO_AVATAR) ?: NO_AVATAR

        )


    override val nick = context.
        getSharedPreferences(USER_INFO, 0).
        getString(LobbyActivity.KEY_USER_NICK, NO_NICK) ?: NO_NICK


    override val isConnectedToWiFi: Boolean
    get() {

        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        return wifiManager.connectionInfo.ipAddress == -1962891072

    }

    override var score = 0



}