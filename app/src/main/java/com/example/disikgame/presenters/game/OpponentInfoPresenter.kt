package com.example.disikgame.presenters.game

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.activities.GameActivity.Companion.opponentProvider
//import com.example.disikgame.activities.GameActivity.Companion.opponentProvider
import com.example.disikgame.threads.CustomThread
import com.example.disikgame.views.game.OpponentInfo
import java.lang.Exception


@InjectViewState
class OpponentInfoPresenter :  MvpPresenter<OpponentInfo>() {

    companion object {

        const val CONNECTED = 0

    }




    fun showOpponentAvatar() = viewState.showOpponentAvatar(opponentProvider.avatarUri)


}