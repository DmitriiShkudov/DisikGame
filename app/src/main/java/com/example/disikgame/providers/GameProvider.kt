package com.example.disikgame.providers

import com.example.disikgame.models.Player

class GameProvider(val player: Player,
                   val opponent: Player) {

    var isGameStarted: Boolean = false

}