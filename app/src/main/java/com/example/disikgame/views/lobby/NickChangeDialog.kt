package com.example.disikgame.views.lobby

import com.arellomobile.mvp.MvpView
import com.example.disikgame.interfaces.Fallible

interface NickChangeDialog : MvpView, Fallible {

    fun showNickDialog()
    fun removeNickDialog()
    fun changeNick(nick: String)
    fun showSuccess()

}