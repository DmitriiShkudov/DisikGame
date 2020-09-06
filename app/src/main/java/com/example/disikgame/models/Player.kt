package com.example.disikgame.models

import android.net.Uri

abstract class Player {

    protected companion object {
        const val NO_AVATAR = "https://bizraise.pro/wp-content/uploads/2014/09/no-avatar.png"
        const val NO_NICK = "Unnamed_player"
    }

    abstract val nick: String
    abstract val avatarUri: Uri
    abstract val isConnectedToWiFi: Boolean?
    abstract var score: Int

}