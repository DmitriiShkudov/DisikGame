package com.example.disikgame.http_client

import com.example.disikgame.http_client.HttpClient.HOST
import com.example.disikgame.http_client.HttpClient.PORT
import com.example.disikgame.providers.GameProvider
import okhttp3.HttpUrl

object ClientRequest {

    val NEW_PLAYER: Pair<String, HttpUrl> =
        "new_player" to

                HttpUrl.Builder()
        .scheme("http")
        .host(HOST)
        .port(PORT)
        .addPathSegment("new_player")
        .addQueryParameter("nick", GameProvider.PlayerProvider.nick)
        .addQueryParameter("avatarUri", GameProvider.PlayerProvider.avatarUri.toString())
        .addQueryParameter("id", GameProvider.PlayerProvider.id.toString())
        .build()



    val GAME_INFO =
        "game_info" to

            HttpUrl.Builder()
        .scheme("http")
        .host(HOST)
        .port(PORT)
        .addPathSegment("game_info")
        .build()



    val REMOVE_PLAYER = "remove_player" to

            HttpUrl.Builder()
        .scheme("http")
        .host(HOST)
        .port(PORT)
        .addPathSegment("remove_player")
        .build()

}