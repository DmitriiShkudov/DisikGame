package com.example.disikgame.presenters.game

import android.content.Context
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.GameActivity
import com.example.disikgame.presenters.lobby.PlayButtonPresenter
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.PlayerInfo
import javax.inject.Inject

@InjectViewState
class PlayerInfoPresenter : MvpPresenter<PlayerInfo>() {

    fun showPlayerAvatar() = viewState.showPlayerAvatar(Uri.parse(PlayerProvider.avatarUri!!))
    fun showPlayerNick() = viewState.showPlayerNick(PlayerProvider.nick!!)

}