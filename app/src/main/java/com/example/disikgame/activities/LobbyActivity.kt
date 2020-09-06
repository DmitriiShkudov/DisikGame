package com.example.disikgame.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.disikgame.R
import com.example.disikgame.http_client.ClientRequest
import com.example.disikgame.http_client.HttpClient
import com.example.disikgame.presenters.lobby.*
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.views.lobby.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lobby.*
import okhttp3.OkHttp
import okhttp3.OkHttpClient


class LobbyActivity : MvpAppCompatActivity(),
    PlayerAvatar,
    PlayButton, PlayerNick,
    NickChangeDialog, AvatarChangeDialog {

    companion object {

        const val KEY_USER_NICK = "USER_NICK"
        const val KEY_USER_AVATAR_URI = "USER_AVATAR_URI"
        const val USER_INFO = "USER"

        fun showToast(context: Context, message: String) =
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        internal lateinit var playerProvider: PlayerProvider
        internal lateinit var gameProvider: GameProvider

    }


    @InjectPresenter
    lateinit var playerAvatarPresenter: PlayerAvatarPresenter

    @InjectPresenter
    lateinit var playButtonPresenter: PlayButtonPresenter

    @InjectPresenter
    lateinit var playerNickPresenter: PlayerNickPresenter

    @InjectPresenter
    lateinit var nickChangeDialogPresenter: NickChangeDialogPresenter

    @InjectPresenter
    lateinit var avatarChangeDialogPresenter: AvatarChangeDialogPresenter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        // define player info and onClicks
        playerProvider = PlayerProvider(applicationContext)
        gameProvider = GameProvider(playerProvider, GameActivity.opponentProvider)




        playerNickTv.setOnLongClickListener {

            nickChangeDialogPresenter.apply{

                this.showDialog()

                mainLayout.setOnClickListener {

                    this.removeDialog()

                }

                btnNewNickApply.setOnClickListener {

                    this.changeNick(inputNewNick.text.toString())

                }

            }

            true

        }

        playerAvatarImageView.setOnLongClickListener {

            avatarChangeDialogPresenter.apply{

                this.showAvatarDialog()

                mainLayout.setOnClickListener {

                    this.removeAvatarDialog()

                }

                btnNewAvatarApply.setOnClickListener {

                    this.changeAvatar(Uri.parse(inputNewAvatar.text.toString()))

                }

            }
            true

        }

        btnPlay.setOnClickListener {

            val httpClient = HttpClient()

            Log.d("GG", httpClient.toString())

            if (httpClient.sendRequest(ClientRequest.NEW_PLAYER) != null) {

                playButtonPresenter.startGame()

            } else {

                Toast.makeText(applicationContext, "Заебулу", Toast.LENGTH_LONG).show()

            }

        }

        playerAvatarPresenter.setupAvatar() // load avatar
        playerNickPresenter.setupNick() // load nick
        playButtonPresenter.startHandleWiFiConnectionStateThread() // handle wi-fi connection

    }

    // avatar

    override fun setupAvatar(uri: Uri) = Picasso.with(applicationContext).load(uri).into(playerAvatarImageView)


    // play button

    override fun readyToPlay() {

        btnPlay.isEnabled = true
        statusBar.text = getString(R.string.ready_to_play)
        cpvStatusBar.visibility = View.GONE

    }

    override fun connecting() {

        btnPlay.isEnabled = false
        statusBar.text = getString(R.string.connecting)
        cpvStatusBar.visibility = View.VISIBLE

    }

    override fun startGame() {

        startActivity(Intent(baseContext, GameActivity::class.java))

    }

    // nick TextView

    override fun setupPlayerNick(nick: String) {

        playerNickTv.text = nick

    }

    // dialog new nick

    override fun showNickDialog() {
        dialogNewNick.visibility = View.VISIBLE
        playerNickTv.isClickable = false
        inputNewNick.requestFocus()
    }

    override fun removeNickDialog() {
        inputNewNick.setText("")
        dialogNewNick.visibility = View.GONE
        playerNickTv.isClickable = true
        mainLayout.setOnClickListener(null)
    }

    override fun changeNick(nick: String) {
        getSharedPreferences(USER_INFO,0).edit().putString(KEY_USER_NICK, nick).apply()
        playerNickTv.text = nick
    }

    override fun showError(message: String) = showToast(applicationContext, message)

    override fun showSuccess() = showToast(applicationContext, "Successful")

    // dialog change avatar

    override fun changeAvatar(uri: Uri) {
        getSharedPreferences(USER_INFO,0).edit().
        putString(KEY_USER_AVATAR_URI, uri.toString()).apply()
        Picasso.with(applicationContext).load(uri).into(playerAvatarImageView)
    }

    override fun showAvatarDialog() {
        dialogNewAvatar.visibility = View.VISIBLE
        playerAvatarImageView.isClickable = false
    }

    override fun removeAvatarDialog() {
        dialogNewAvatar.visibility = View.GONE
        playerAvatarImageView.isClickable = true
    }

}