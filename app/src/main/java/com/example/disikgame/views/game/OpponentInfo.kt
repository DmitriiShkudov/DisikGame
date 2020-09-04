package com.example.disikgame.views.game

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.example.disikgame.interfaces.Connectable


interface OpponentInfo : MvpView, Connectable {

    fun showOpponentAvatar(uri: Uri)
    fun showOpponentNick(nick: String)

}