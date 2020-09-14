package com.example.disikgame.presenters.game

import android.net.Uri
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.OpponentInfo
import java.lang.Exception


@InjectViewState
class OpponentInfoPresenter :  MvpPresenter<OpponentInfo>() {

    fun showOpponentAvatar() = viewState.showOpponentAvatar(Uri.parse(GameProvider.OpponentProvider.avatarUri!!))
    fun showOpponentNick() = viewState.showOpponentNick(GameProvider.OpponentProvider.nick!!)

}