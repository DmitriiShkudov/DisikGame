package com.example.disikgame.views.game

import com.arellomobile.mvp.MvpView

interface GameInfo: MvpView {

    fun setPlayerScore(score: Int)
    fun setOpponentScore(score: Int)
    fun playerIsGuessing()
    fun playerIsGuess()
    fun opponentIsGuessing()
    fun opponentIsGuess()
    fun setRaceTo(raceTo: Int)
    fun waitingTheOpponent()
    fun lostConnection()
    fun gameIsGoing()

}