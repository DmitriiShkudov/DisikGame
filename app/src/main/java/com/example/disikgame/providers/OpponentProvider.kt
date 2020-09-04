package com.example.disikgame.providers

import android.net.Uri
import com.example.disikgame.http_client.httpClient
import com.example.disikgame.models.Player

class OpponentProvider() : Player() {

    override val nick = ""
    override val avatarUri = Uri.parse("")
    override val isConnectedToServer = httpClient.isOpponentConnected()
    override val isConnectedToWiFi = null

}