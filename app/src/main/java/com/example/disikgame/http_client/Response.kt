package com.example.disikgame.http_client

object Response {

    object NewPlayer {

        const val FIRST_PLAYER_ADDED = "First player is added"
        const val FIRST_PLAYER_ADDED_NUM = 1
        const val GAME_IS_STARTED = "Game is started"
        const val GAME_IS_STARTED_NUM = 2
        const val ALREADY_TWO_PLAYERS = "There're already 2 players in the game!"
        const val ALREADY_TWO_PLAYERS_NUM = 3

    }

    object RemovePlayer {

        const val REMOVE_PLAYER = "Remove player"
        const val REMOVE_PLAYER_NUM = 4

    }

    object Update {

        const val UPDATE = "Update"
        const val UPDATE_NUM = 5

    }


}