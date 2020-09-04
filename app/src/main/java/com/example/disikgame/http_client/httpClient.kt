package com.example.disikgame.http_client

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception

object httpClient : OkHttpClient() {

    const val URL = "http://localhost:8080/"

    private val request = Request.
                                    Builder().
                                    url(URL).
                                    build()

    private val body: String?
    get() = try {

        this.newCall(request).execute().use { response: Response -> response.body!!.string()

        }

    } catch(e:Exception) {null}

    fun isPlayerConnected(): Boolean {

        Thread { Log.d("BODY---->", body ?: "NO BODY") }.start()

            return true

    }

    fun isOpponentConnected(): Boolean {

        Thread { Log.d("BODY---->", body ?: "NO BODY") }.start()

        return false

    }

    fun isGameStarted() {



    }

    fun notifyGameHasBeenStarted() {



    }
}




