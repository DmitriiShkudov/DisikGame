package com.example.disikgame.presenters.lobby

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.views.lobby.PlayerNick

@InjectViewState
class PlayerNickPresenter : MvpPresenter<PlayerNick>() {

    fun setupNick() = viewState.setupPlayerNick(playerProvider.nick)

}