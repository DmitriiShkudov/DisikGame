package com.example.disikgame.providers

import com.fasterxml.jackson.annotation.JsonProperty

open class Game() {

    @JsonProperty var player1: Player? = null
    @JsonProperty var player2: Player? = null
    @JsonProperty var guessedNumber: Int? = null
    @JsonProperty var raceTo: Int? = null

    constructor(player1: Player?,
                player2: Player?,
                guessedNumber: Int?,
                raceTo: Int?) : this() {

        this.player1 = player1
        this.player2 = player2
        this.guessedNumber = guessedNumber
        this.raceTo = raceTo

    }

}