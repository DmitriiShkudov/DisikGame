package com.example.disikgame.http_client

import com.example.disikgame.activities.LobbyActivity.Companion.gameProvider
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL


class HttpClient : OkHttpClient() {

    val GAME_INFO = "/gameinfo"
    val NEW_PLAYER = "/newplayer"


    var url = HttpUrl.Builder()
        .scheme("https")
        .host("192.168.0.138")
        .addQueryParameter("name", gameProvider.player.nick)
        .addQueryParameter("avatarUri", gameProvider.player.avatarUri.toString())
        .build()


    private var requestNewPlayer = Request.
                                    Builder().
                                    url(url).
                                    build()

    /*private val requestGameInfo = Request.
                                    Builder().
                                    url(URL + GAME_INFO).
                                    build() */

    fun sendRequest(request: ClientRequest): String? =

            when (request) {

                ClientRequest.NEW_PLAYER -> try {

                    this.newCall(requestNewPlayer).execute().toString()

                } catch (e: Exception) {
                    null
                }

                /*ClientRequest.GAME_INFO -> try {

                    this.newCall(requestGameInfo).execute().toString()

                } catch (e: Exception) {
                    null
                } */

                else -> null
            }



}




