package com.example.disikgame.presenters.lobby

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Handler
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.LobbyActivity
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.lobby.PlayButton

@InjectViewState
class PlayButtonPresenter() : MvpPresenter<PlayButton>() {

    companion object {

        const val CONNECTED = 0

    }




    private val handler = Handler {

        when (it.what) {

            CONNECTED -> viewState.readyToPlay()
            else -> viewState.connecting()

        }

        (true)

    }

    private val handleWifiConnection = CustomThread(
        message = CustomThread.Message.CONNECTION_TO_WIFI_STATE,
        handler = this.handler) {

        playerProvider.isConnectedToWiFi

    }

    fun startHandleWiFiConnectionStateThread() = this.handleWifiConnection.start()


    fun startGame() = viewState.startGame()

}
