package com.example.disikgame.views.lobby

import android.net.Uri
import com.arellomobile.mvp.MvpView
import com.example.disikgame.interfaces.Fallible

interface AvatarChangeDialog : MvpView,
    Fallible {

    fun showAvatarDialog()
    fun removeAvatarDialog()
    fun changeAvatar(uri: Uri)
    fun showSuccess()

}