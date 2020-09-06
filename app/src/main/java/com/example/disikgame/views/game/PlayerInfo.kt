package com.example.disikgame.views.game

import android.net.Uri
import com.arellomobile.mvp.MvpView

interface PlayerInfo : MvpView {

    fun showPlayerAvatar(uri: Uri)
    fun showPlayerNick(nick: String)

}