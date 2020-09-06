package com.example.disikgame.http_client

import com.example.disikgame.activities.GameActivity
import com.example.disikgame.activities.LobbyActivity.Companion.gameProvider
import com.example.disikgame.providers.GameProvider
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception

class HttpClient : OkHttpClient() {



    val URL = "192.168.0.138"
    val GAME_INFO = "/gameinfo"
    val NEW_PLAYER = "/newplayer"

    private val httpUrlNewPlayer = HttpUrl.Builder().
        addQueryParameter("name", gameProvider.player.nick).
        addQueryParameter("avatarUri", gameProvider.player.avatarUri.toString()).host(URL).
        addPathSegment(NEW_PLAYER).scheme("http://").build()


    private var requestNewPlayer = Request.
                                    Builder().
                                    url(httpUrlNewPlayer).
                                    build()

    private val requestGameInfo = Request.
                                    Builder().
                                    url(URL + GAME_INFO).
                                    build()


    fun sendRequest(request: ClientRequest): String? =

            when (request) {

            ClientRequest.NEW_PLAYER -> try {

                this.newCall(requestNewPlayer).execute().toString()

            } catch (e: Exception) {null}

            ClientRequest.GAME_INFO -> try {

                this.newCall(requestGameInfo).execute().toString()

            } catch (e: Exception) {null}

    }



}




