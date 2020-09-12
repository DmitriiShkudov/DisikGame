package com.example.disikgame.presenters.game

import android.net.Uri
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.providers.OpponentProvider
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.OpponentInfo
import java.lang.Exception


@InjectViewState
class OpponentInfoPresenter :  MvpPresenter<OpponentInfo>() {

    fun showOpponentAvatar() = viewState.showOpponentAvatar(Uri.parse(OpponentProvider.avatarUri!!))
    fun showOpponentNick() = viewState.showOpponentNick(OpponentProvider.nick!!)

}