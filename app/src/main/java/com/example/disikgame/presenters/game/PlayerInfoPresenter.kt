package com.example.disikgame.presenters.game

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.presenters.lobby.PlayButtonPresenter
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.PlayerInfo
import javax.inject.Inject

@InjectViewState
class PlayerInfoPresenter : MvpPresenter<PlayerInfo>() {

    companion object {

        const val CONNECTED = 0

    }

    private val handler = Handler {

        when (it.what) {

            CONNECTED -> viewState.connected()
            else -> viewState.disconnected()

        }

        (true)

    }

    private val handleConnectionStateThread = CustomThread(
        message = CustomThread.Message.CONNECTION_TO_SERVER_STATE,
        handler = this.handler) {

        playerProvider.isConnectedToServer && playerProvider.isConnectedToWiFi

    }

    fun showPlayerAvatar() = viewState.showPlayerAvatar(playerProvider.avatarUri)
    fun showPlayerNick() = viewState.showPlayerNick(playerProvider.nick)

    fun startHandleConnectionStateThread() = this.handleConnectionStateThread.start()
    fun interruptHandleConnectionStateThread() = this.handleConnectionStateThread.interrupt()




}