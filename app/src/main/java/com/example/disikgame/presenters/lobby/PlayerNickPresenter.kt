package com.example.disikgame.presenters.lobby

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.views.lobby.PlayerNick

@InjectViewState
class PlayerNickPresenter : MvpPresenter<PlayerNick>() {

    fun setupNick() = viewState.setupPlayerNick(GameProvider.PlayerProvider.nick!!)

}