package com.example.disikgame.providers

import com.example.disikgame.activities.GameActivity.Companion.opponentProvider
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.models.Player

class GameProvider(val player: Player = playerProvider,
                   val opponent: Player = opponentProvider) {

    var isGameStarted: Boolean = false
    val raceTo = 7
    var isWaitingTheOpponent = false
    var isLostConnection = false

}