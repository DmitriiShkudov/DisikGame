package com.example.disikgame.presenters.lobby

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.views.lobby.PlayerAvatar

@InjectViewState
class PlayerAvatarPresenter(): MvpPresenter<PlayerAvatar>() {

    fun setupAvatar() = viewState.setupAvatar(Uri.parse(GameProvider.PlayerProvider.avatarUri!!))


}