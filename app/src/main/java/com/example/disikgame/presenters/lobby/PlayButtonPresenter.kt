package com.example.disikgame.presenters.lobby

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
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

        !GameProvider.PlayerProvider.isLostConnection

    }

    fun startHandleWiFiConnectionStateThread() = this.handleWifiConnection.start()
}
