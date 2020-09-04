package com.example.disikgame.presenters.lobby

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.views.lobby.NickChangeDialog

@InjectViewState
class NickChangeDialogPresenter : MvpPresenter<NickChangeDialog>() {

    fun showDialog() = viewState.showNickDialog()
    fun removeDialog() = viewState.removeNickDialog()

    fun changeNick(nick: String) {

        if (nick.length >= 3) {

            viewState.changeNick(nick)
            viewState.removeNickDialog()
            viewState.showSuccess()

        } else {

            viewState.showError("Too short nickname!")

        }
    }

}