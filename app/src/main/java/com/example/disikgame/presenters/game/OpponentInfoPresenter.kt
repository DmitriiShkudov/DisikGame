package com.example.disikgame.presenters.game

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.GameActivity.Companion.opponentProvider
//import com.example.disikgame.activities.GameActivity.Companion.opponentProvider
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.OpponentInfo


@InjectViewState
class OpponentInfoPresenter :  MvpPresenter<OpponentInfo>() {

    companion object {

        const val CONNECTED = 0

    }

    private val handler: Handler = Handler {

        when (it.what) {

            CONNECTED -> {

                this.interruptWaitingAnOpponentThread()
                viewState.connected()

            }
            else -> viewState.disconnected()

        }

        (true)

    }

    private val waitingAnOpponentThread = CustomThread(
        message = CustomThread.Message.CONNECTION_TO_SERVER_STATE,
        handler = this@OpponentInfoPresenter.handler) {

        opponentProvider.isConnectedToServer

    }


    fun showOpponentAvatar() = viewState.showOpponentAvatar(opponentProvider.avatarUri)

    fun startWaitingAnOpponentThread() = this.waitingAnOpponentThread.start()
    fun interruptWaitingAnOpponentThread() = this.waitingAnOpponentThread.interrupt()

}