package com.example.disikgame.threads

import android.os.Handler
import android.util.Log
//import com.example.disikgame.presenters.game.OpponentInfoPresenter
import com.example.disikgame.presenters.game.PlayerInfoPresenter
import com.example.disikgame.presenters.lobby.PlayButtonPresenter

class CustomThread() : Thread() {

    private lateinit var customRun : () -> Unit

    enum class Message {

        CONNECTION_TO_SERVER_STATE, CONNECTION_TO_WIFI_STATE, OPPONENT_CONNECTION_STATE

    }

    override fun run() = this.customRun.invoke()


    constructor(message: Message, handler: Handler, action: () -> Any?) : this() {

        when (message) {

            Message.CONNECTION_TO_WIFI_STATE ->

                this.customRun = {

                    while (true) {

                        if (action.invoke() as Boolean) {

                            handler.sendEmptyMessage(PlayButtonPresenter.CONNECTED)

                        } else {

                            handler.sendEmptyMessage(-1)

                        }

                        sleep(500)

                    }

            }

            Message.CONNECTION_TO_SERVER_STATE ->

                this.customRun = {

                    while (true) {

                        if (action.invoke() as Boolean) {

                            handler.sendEmptyMessage(PlayerInfoPresenter.CONNECTED)

                        } else {

                            handler.sendEmptyMessage(-1)

                        }

                        sleep(1250)

                    }

                }

            Message.OPPONENT_CONNECTION_STATE -> {

                this.customRun = {

                    while (true) {

                        if (action.invoke() as Boolean) {

                            //handler.sendEmptyMessage(OpponentInfoPresenter.CONNECTED)

                        } else {

                            handler.sendEmptyMessage(-1)

                        }

                        sleep(300)

                    }

                }

            }

        }

    }

}