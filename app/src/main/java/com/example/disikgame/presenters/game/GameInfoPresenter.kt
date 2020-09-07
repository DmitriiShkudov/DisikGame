package com.example.disikgame.presenters.game

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.GameActivity.Companion.opponentProvider
import com.example.disikgame.activities.LobbyActivity.Companion.gameProvider
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.views.game.GameInfo

@InjectViewState
class GameInfoPresenter: MvpPresenter<GameInfo>() {

    fun setPlayerScore() = viewState.setPlayerScore(playerProvider.score)
    fun setOpponentScore() = viewState.setOpponentScore(opponentProvider.score)
    fun setRaceTo() = viewState.setRaceTo(gameProvider.raceTo)
    fun playerIsGuessing() = viewState.playerIsGuessing()
    fun opponentIsGuessing() = viewState.opponentIsGuessing()
    fun opponentIsGuess() = viewState.opponentIsGuess()

    fun waitingTheOpponent() {

        if (!gameProvider.isLostConnection) {

            viewState.waitingTheOpponent()
            gameProvider.isWaitingTheOpponent = true

        }

    }

    fun lostConnection() {

        gameProvider.isLostConnection = true
        viewState.lostConnection()

    }

    fun gameIsGoing() {

        gameProvider.isLostConnection = false
        gameProvider.isWaitingTheOpponent = false
        viewState.gameIsGoing()

    }
}