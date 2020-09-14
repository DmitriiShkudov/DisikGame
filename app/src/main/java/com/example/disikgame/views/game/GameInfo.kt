package com.example.disikgame.views.game

import com.arellomobile.mvp.MvpView

interface GameInfo: MvpView {

    fun setPlayerScore(score: Int)
    fun setOpponentScore(score: Int)
    fun playerIsGuessing(guessing: Boolean)
    fun playerIsGuess(guessed: Boolean)
    fun opponentIsGuessing(guessing: Boolean)
    fun opponentIsGuess(guessed: Boolean)
    fun setRaceTo(raceTo: Int)
    fun waitingTheOpponent()
    fun lostConnection()
    fun gameIsGoing()

}