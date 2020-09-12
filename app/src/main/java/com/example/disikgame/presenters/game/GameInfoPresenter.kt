package com.example.disikgame.presenters.game

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.providers.OpponentProvider
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.views.game.GameInfo


@InjectViewState
class GameInfoPresenter: MvpPresenter<GameInfo>() {

    fun setPlayerScore() = viewState.setPlayerScore(PlayerProvider.score)
    fun setOpponentScore() = viewState.setOpponentScore(OpponentProvider.score)
    fun setRaceTo() = viewState.setRaceTo(GameProvider.raceTo ?: 0)
    fun playerIsGuessing() = viewState.playerIsGuessing()
    fun opponentIsGuessing() = viewState.opponentIsGuessing()
    fun opponentIsGuess() = viewState.opponentIsGuess()

    fun waitingTheOpponent() {

        if (!PlayerProvider.isWaitingTheOpponent) {

            viewState.waitingTheOpponent()
            PlayerProvider.isWaitingTheOpponent = true

        }

    }

    fun lostConnection() {

        PlayerProvider.isLostConnection = true
        viewState.lostConnection()

    }

    fun gameIsGoing() {

        PlayerProvider.isLostConnection = false
        PlayerProvider.isWaitingTheOpponent = false
        viewState.gameIsGoing()

    }
}