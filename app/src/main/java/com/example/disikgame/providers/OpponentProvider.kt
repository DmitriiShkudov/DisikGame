package com.example.disikgame.providers

import android.net.Uri
import com.example.disikgame.models.Player

class OpponentProvider() : Player() {

    override val nick = "Nikitoslav"
    override val avatarUri =
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSiAgXUUhw3O6hBE-TwucHs9V74LqjrtMMkcQ&usqp=CAU")
    override val isConnectedToWiFi = null
    override var score = 4

}