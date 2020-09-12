package com.example.disikgame.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.disikgame.R
import com.example.disikgame.http_client.ClientRequest
import com.example.disikgame.http_client.HttpClient
import com.example.disikgame.http_client.Response
import com.example.disikgame.presenters.lobby.*
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.providers.Player
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.providers.PlayerProvider.NO_AVATAR
import com.example.disikgame.views.lobby.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_lobby.*
import java.util.*


class LobbyActivity : MvpAppCompatActivity(),
    PlayerAvatar,
    PlayButton, PlayerNick,
    NickChangeDialog, AvatarChangeDialog {

    companion object {

        const val KEY_USER_NICK = "USER_NICK"
        const val KEY_USER_AVATAR_URI = "USER_AVATAR_URI"
        const val USER_INFO = "USER"



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

    val handler = Handler {

        when (it.what) {

            Response.NewPlayer.FIRST_PLAYER_ADDED_NUM -> {

                Log.d("LOBBY_HANDLER", "FIRST PLAYER ADDED")
                val intent = Intent(this@LobbyActivity, GameActivity::class.java)
                intent.action = Response.NewPlayer.FIRST_PLAYER_ADDED
                startActivity(intent)

            }

            Response.NewPlayer.GAME_IS_STARTED_NUM -> {

                Log.d("LOBBY_HANDLER", "GAME IS STARTED")
                val intent = Intent(this@LobbyActivity, GameActivity::class.java)
                intent.action = Response.NewPlayer.GAME_IS_STARTED
                startActivity(intent)

            }

            Response.NewPlayer.ALREADY_TWO_PLAYERS_NUM -> {

                Log.d("LOBBY_HANDLER", "ALREADY TWO PLAYERS")
                Toast.makeText(applicationContext, "There are two players already in the game",
                    Toast.LENGTH_LONG).show()

            }
            else -> {

                val intent = Intent(this@LobbyActivity, GameActivity::class.java)
                intent.action = Response.NewPlayer.GAME_IS_STARTED
                startActivity(intent)

            }

        }
        true

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)

        // define player info and onClicks
        initPlayer()
        HttpClient.lobbyHandler = this.handler

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

            HttpClient.sendRequest(ClientRequest.NEW_PLAYER.first)

        }

        playerAvatarPresenter.setupAvatar() // load avatar
        playerNickPresenter.setupNick() // load nick
        playButtonPresenter.startHandleWiFiConnectionStateThread() // handle wi-fi connection

    }

    override fun onResume() {
        if (PlayerProvider.isWaitingTheOpponent) {
            PlayerProvider.isWaitingTheOpponent = false
            HttpClient.sendRequest(ClientRequest.REMOVE_PLAYER.first)
        }
        super.onResume()
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
        initPlayer()
        playerNickTv.text = nick
    }

    override fun showError(message: String) =
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    override fun showSuccess() =
        Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT).show()

    // dialog change avatar

    override fun changeAvatar(uri: Uri) {
        getSharedPreferences(USER_INFO,0).edit().
        putString(KEY_USER_AVATAR_URI, uri.toString()).apply()
        initPlayer()
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

    private fun initPlayer() {

        PlayerProvider.context = applicationContext

        PlayerProvider.nick = getSharedPreferences(USER_INFO, 0)?.
            getString(KEY_USER_NICK, PlayerProvider.NO_NICK) ?: PlayerProvider.NO_NICK

        PlayerProvider.id = Random().nextLong()
        Log.d("SETUP ID: ", PlayerProvider.id.toString())

        PlayerProvider.avatarUri =

            getSharedPreferences(USER_INFO, 0).getString(KEY_USER_AVATAR_URI, PlayerProvider.NO_AVATAR)
                ?: PlayerProvider.NO_AVATAR

    }

}