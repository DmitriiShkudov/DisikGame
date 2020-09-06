package com.example.disikgame.providers

import android.net.Uri
import com.example.disikgame.models.Player

class OpponentProvider() : Player() {

    override val nick = "Nikitoslav"
    override val avatarUri = Uri.parse("https://image.dhgate.com/0x0s/f2-albu-g10-M01-01-F0-rBVaWV5CGV6Af0XNAAB0iRIZxFs762.jpg/realistic-shit-gift-funny-toys-fake-poop.jpg")
    override val isConnectedToWiFi = null
    override var score = 0

}