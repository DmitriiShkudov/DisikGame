package com.example.disikgame.presenters.lobby

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.disikgame.views.lobby.AvatarChangeDialog

@InjectViewState
class AvatarChangeDialogPresenter : MvpPresenter<AvatarChangeDialog>() {

    fun showAvatarDialog() = viewState.showAvatarDialog()
    fun removeAvatarDialog() = viewState.removeAvatarDialog()

    fun changeAvatar(uri: Uri) {

        if (uri.toString().isNotEmpty()) {

            if (
                uri.lastPathSegment!!.contains(".png") ||
                uri.lastPathSegment!!.contains(".jpg") ||
                uri.lastPathSegment!!.contains(".jpeg")
            ) {

                viewState.changeAvatar(uri)
                viewState.removeAvatarDialog()
                viewState.showSuccess()

            } else {

                viewState.showError("Incorrect image URL!")

            }

        } else {

            viewState.showError("Enter image URL!")

        }
    }
}