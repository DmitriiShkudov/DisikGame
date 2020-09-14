package com.example.disikgame.presenters.game

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.views.game.GameInfo


@InjectViewState
class GameInfoPresenter: MvpPresenter<GameInfo>() {

    fun setPlayerScore() = viewState.setPlayerScore(GameProvider.PlayerProvider.score)
    fun setOpponentScore() = viewState.setOpponentScore(GameProvider.OpponentProvider.score)
    fun setRaceTo() = viewState.setRaceTo(GameProvider.raceTo ?: 0)
    fun playerIsGuessing() = viewState.playerIsGuessing(GameProvider.PlayerProvider.guessing)
    fun playerIsGuess() = viewState.playerIsGuessing(GameProvider.PlayerProvider.guessed)
    fun opponentIsGuessing() = viewState.opponentIsGuessing(GameProvider.OpponentProvider.guessing)
    fun opponentIsGuess() = viewState.opponentIsGuess(GameProvider.OpponentProvider.guessed)

        fun waitingTheOpponent() {

            if (!GameProvider.PlayerProvider.isWaitingTheOpponent) {

                viewState.waitingTheOpponent()
                GameProvider.PlayerProvider.isWaitingTheOpponent = true

            }

        }

        fun lostConnection() {

            GameProvider.PlayerProvider.isLostConnection = true
            viewState.lostConnection()

        }

        fun gameIsGoing() {

            GameProvider.PlayerProvider.isLostConnection = false
            GameProvider.PlayerProvider.isWaitingTheOpponent = false
            viewState.gameIsGoing()

        }
    }