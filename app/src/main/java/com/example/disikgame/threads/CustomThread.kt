package com.example.disikgame.threads

import android.os.Handler
import android.util.Log
import com.example.disikgame.activities.GameActivity
import com.example.disikgame.http_client.Response
import com.example.disikgame.presenters.game.OpponentInfoPresenter
//import com.example.disikgame.presenters.game.OpponentInfoPresenter
import com.example.disikgame.presenters.game.PlayerInfoPresenter
import com.example.disikgame.presenters.lobby.PlayButtonPresenter
import com.example.disikgame.providers.Game
import okio.IOException
import java.lang.Exception
import kotlin.concurrent.timer
import okhttp3.internal.wait as wait

class CustomThread() : Thread() {

    private lateinit var customRun : () -> Unit

    enum class Message {

        CONNECTION_TO_WIFI_STATE, WAITING_THE_OPPONENT, GAME_INFO

    }

    override fun run() = this.customRun.invoke()


    constructor(message: Message, handler: Handler, action: () -> Any?) : this() {

        this.customRun = when (message) {

            Message.CONNECTION_TO_WIFI_STATE -> {
                {

                        while (true) {

                            if (action.invoke() as Boolean) {

                                handler.sendEmptyMessage(PlayButtonPresenter.CONNECTED)

                            } else {

                                handler.sendEmptyMessage(-1)

                            }

                            sleep(500)

                        }
                    }
                }

            Message.WAITING_THE_OPPONENT -> {
                    {

                            while (true) {

                                if (action.invoke() as Boolean) {

                                    handler.sendEmptyMessage(GameActivity.OPPONENT_IS_CONNECTED)
                                    Log.d("DEBUG", "Оппонент нашелся в потоке!")
                                    sleep(1000000000) // ИСПРАВИТЬ

                                }

                                try {
                                    sleep(700)
                                } catch (e: InterruptedException) {}

                            }

                }
            }
            Message.GAME_INFO -> {

                    {

                        while (true) {

                            if (action.invoke() as Boolean) {

                                handler.sendEmptyMessage(Response.NewPlayer.GAME_IS_STARTED_NUM)
                                Log.d("b","h")
                                sleep(100000000) // ИСПРАВИТЬ

                            }

                            try {
                                sleep(700)
                            } catch (e: InterruptedException) {}

                        }

                    }

            }
        }
    }
}