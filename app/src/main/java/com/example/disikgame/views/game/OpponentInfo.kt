package com.example.disikgame.views.game

import android.net.Uri
import com.arellomobile.mvp.MvpView


interface OpponentInfo : MvpView {

    fun showOpponentAvatar(uri: Uri)
    fun showOpponentNick(nick: String)


}