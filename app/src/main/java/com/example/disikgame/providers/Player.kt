package com.example.disikgame.providers

import com.fasterxml.jackson.annotation.JsonProperty


open class Player() {

    @JsonProperty open var nick: String? = null
    @JsonProperty open var avatarUri: String? = null
    @JsonProperty open var id: Long? = null
    @JsonProperty open var score: Int = 0
    @JsonProperty open var guessing: Boolean = false
    @JsonProperty open var guessed: Boolean = false
    @JsonProperty open var isWaitingTheOpponent: Boolean = false
    @JsonProperty open var isLostConnection: Boolean = false

    constructor(nick: String?,
                avatarUri: String?,
                id: Long?,
                score: Int = 0,
                guessing: Boolean = false,
                guessed: Boolean = false,
                isWaitingTheOpponent: Boolean = false,
                isLostConnection: Boolean = false) : this() {

        this.nick = nick
        this.avatarUri = avatarUri
        this.id = id
        this.score = score
        this.guessing = guessing
        this.guessed = guessed
        this.isWaitingTheOpponent = isWaitingTheOpponent
        this.isLostConnection = isLostConnection

    }

}