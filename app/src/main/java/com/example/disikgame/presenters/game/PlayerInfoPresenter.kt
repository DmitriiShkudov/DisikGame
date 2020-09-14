package com.example.disikgame.presenters.game

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.views.game.PlayerInfo

@InjectViewState
class PlayerInfoPresenter : MvpPresenter<PlayerInfo>() {

    fun showPlayerAvatar() = viewState.showPlayerAvatar(Uri.parse(GameProvider.PlayerProvider.avatarUri!!))
    fun showPlayerNick() = viewState.showPlayerNick(GameProvider.PlayerProvider.nick!!)

}