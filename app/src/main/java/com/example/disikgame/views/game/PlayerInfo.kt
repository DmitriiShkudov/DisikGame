package com.example.disikgame.views.game

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.example.disikgame.interfaces.Connectable

interface PlayerInfo : MvpView, Connectable {

    fun showPlayerAvatar(uri: Uri)
    fun showPlayerNick(nick: String)

}