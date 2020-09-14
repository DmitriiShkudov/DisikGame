package com.example.disikgame.http_client

import android.os.Handler
import android.util.Log
import com.example.disikgame.providers.Game
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.threads.CustomThread
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Thread.sleep


object HttpClient : OkHttpClient() {

    const val HOST = "172.19.124.12"
    const val PORT = 8000

    lateinit var lobbyHandler: Handler
    lateinit var gameHandler: Handler

    private val requestNewPlayer = Request.
        Builder().
        url(ClientRequest.NEW_PLAYER.second).
        build()

    private val requestGameInfo = Request.
        Builder().
        url(ClientRequest.GAME_INFO.second).
        build()

    private val requestRemovePlayer = Request.
        Builder().
        url(ClientRequest.REMOVE_PLAYER.second).
        build()

    fun sendRequest(request: String) =

        when (request) {

            ClientRequest.NEW_PLAYER.first -> try {

                Log.d("DEBUG", "-- New player request --")

                Thread {

                    val body = this.newCall(requestNewPlayer).execute().body!!.string()
                    Log.d("BODY = ", body)
                    when (body) {

                        Response.NewPlayer.FIRST_PLAYER_ADDED -> {

                            this.lobbyHandler.sendEmptyMessage(Response.NewPlayer.FIRST_PLAYER_ADDED_NUM)
                            sleep(500)
                            CustomThread(CustomThread.Message.WAITING_THE_OPPONENT, this.lobbyHandler
                            ) {

                                try {

                                    Log.d("DEBUG", "Запрашиваю GameInfo (жду энь блень)")

                                    val gameInfoJSON =
                                        this.newCall(requestGameInfo).execute().body!!.string()

                                    val receivedPlayer =
                                        ObjectMapper().readValue(gameInfoJSON, Game::class.java).player2

                                    Log.d("Сост. оппонента:", receivedPlayer.toString())

                                    if (receivedPlayer != null) {

                                        GameProvider.OpponentProvider.nick = receivedPlayer.nick
                                        GameProvider.OpponentProvider.avatarUri = receivedPlayer.avatarUri

                                        true

                                    } else false


                                } catch (e: Throwable) {

                                    Log.d("DID NOT CONNECT TO THE SERVER:", e.message!!)
                                    false

                                }

                            }.start()
                        }


                        Response.NewPlayer.GAME_IS_STARTED -> {
                            CustomThread(CustomThread.Message.GAME_INFO, this.lobbyHandler
                            ) {

                                try {

                                    Log.d("DEBUG", "Запрашиваю GameInfo (жду энь блень)")

                                    val gameInfoJSON =
                                        this.newCall(requestGameInfo).execute().body!!.string()

                                    val receivedGameInfo =
                                        ObjectMapper().readValue(gameInfoJSON, Game::class.java)

                                    Log.d("", "------------------------------")
                                    Log.d("", "------------------------------")
                                    Log.d("GAME_INFO player1 is Null", (receivedGameInfo.player1 == null).toString())
                                    Log.d("GAME_INFO player2 is Null", (receivedGameInfo.player2 == null).toString())
                                    Log.d("GAME_INFO player1 score", ((receivedGameInfo.player1?.score ?: -1).toString()))
                                    Log.d("GAME_INFO player2 score", ((receivedGameInfo.player2?.score ?: -1).toString()))
                                    Log.d("", "------------------------------")
                                    Log.d("", "------------------------------")


                                    GameProvider.player1 = receivedGameInfo.player1
                                    GameProvider.player2 = receivedGameInfo.player2
                                    GameProvider.raceTo = receivedGameInfo.raceTo

                                    GameProvider.OpponentProvider.nick = receivedGameInfo.player1?.nick
                                    GameProvider.OpponentProvider.avatarUri = receivedGameInfo.player1?.avatarUri

                                    GameProvider.PlayerProvider.nick = receivedGameInfo.player2?.nick
                                    GameProvider.PlayerProvider.avatarUri = receivedGameInfo.player2?.avatarUri

                                    true

                                } catch (e: Throwable) {

                                    Log.d("DID NOT CONNECT TO THE SERVER:", e.message!!)
                                    false

                                }

                            }.start()
                            sleep(500)


                        }

                        Response.Update.UPDATE -> {

                            val gameInfoJSON =
                                this.newCall(requestGameInfo).execute().body!!.string()

                            val receivedGameInfo =
                                ObjectMapper().readValue(gameInfoJSON, Game::class.java)

                            GameProvider.PlayerProvider.score = receivedGameInfo.player1!!.score
                            GameProvider.OpponentProvider.score = receivedGameInfo.player2!!.score

                            GameProvider.PlayerProvider.guessing = receivedGameInfo.player1!!.guessing
                            GameProvider.OpponentProvider.guessing = receivedGameInfo.player2!!.guessing

                            GameProvider.PlayerProvider.guessed = receivedGameInfo.player1!!.guessed
                            GameProvider.OpponentProvider.guessed = receivedGameInfo.player2!!.guessed

                        }

                        Response.NewPlayer.ALREADY_TWO_PLAYERS ->
                            this.lobbyHandler.sendEmptyMessage(Response.NewPlayer.ALREADY_TWO_PLAYERS_NUM)

                    }

                }.start()

            } catch (e: Exception) {
                e.printStackTrace()
                this.lobbyHandler.sendEmptyMessage(-1)
            }

            ClientRequest.REMOVE_PLAYER.first -> try {

                Thread {
                    this.newCall(requestRemovePlayer).execute()
                }.start()

            } catch (e: Exception) {
                e.printStackTrace()
                this.lobbyHandler.sendEmptyMessage(-1)
            }

            else -> null
        }
}