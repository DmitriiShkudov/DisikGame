package com.example.disikgame.views.lobby

import com.arellomobile.mvp.MvpView

interface PlayButton : MvpView {

    fun readyToPlay()
    fun connecting()

}