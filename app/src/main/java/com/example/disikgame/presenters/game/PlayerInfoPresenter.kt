package com.example.disikgame.presenters.game

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.GameActivity
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.presenters.lobby.PlayButtonPresenter
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.PlayerInfo
import javax.inject.Inject

@InjectViewState
class PlayerInfoPresenter : MvpPresenter<PlayerInfo>() {

    fun showPlayerAvatar() = viewState.showPlayerAvatar(playerProvider.avatarUri)
    fun showPlayerNick() = viewState.showPlayerNick(playerProvider.nick)

}