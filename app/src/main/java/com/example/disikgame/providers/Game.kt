package com.example.disikgame.providers

import com.fasterxml.jackson.annotation.JsonProperty

open class Game() {

    @JsonProperty var player1: Player? = null
    @JsonProperty var player2: Player? = null
    @JsonProperty var guessedNumber: Int? = null
    @JsonProperty var chosenNumber: Int? = null
    @JsonProperty var raceTo: Int? = null
    @JsonProperty var gameIsStarted: Boolean = false

    constructor(guessedNumber: Int?,
                chosenNumber: Int?,
                raceTo: Int?,
                gameIsStarted: Boolean) : this() {

        this.guessedNumber = guessedNumber
        this.chosenNumber = guessedNumber
        this.raceTo = raceTo
        this.gameIsStarted = gameIsStarted

    }

}