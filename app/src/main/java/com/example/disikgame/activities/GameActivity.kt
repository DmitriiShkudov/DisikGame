package com.example.disikgame.activities

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.disikgame.R
import com.example.disikgame.http_client.httpClient
import com.example.disikgame.models.Player
import com.example.disikgame.presenters.game.OpponentInfoPresenter
import com.example.disikgame.presenters.game.PlayerInfoPresenter
import com.example.disikgame.providers.OpponentProvider
import com.example.disikgame.views.game.OpponentInfo
import com.example.disikgame.views.game.PlayerInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : MvpAppCompatActivity(), PlayerInfo, OpponentInfo {


    companion object {

        internal val opponentProvider = OpponentProvider()

    }

    @InjectPresenter
    lateinit var opponentInfoPresenter: OpponentInfoPresenter


    @InjectPresenter
    lateinit var playerInfoPresenter: PlayerInfoPresenter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        playerInfoPresenter.startHandleConnectionStateThread()
        opponentInfoPresenter.startWaitingAnOpponentThread()


    }

    // player info

    override fun showPlayerAvatar(uri: Uri) = Picasso.with(applicationContext).load(uri).into(imgPlayerAvatar)


    override fun showPlayerNick(nick: String) {

        tvPlayerNick.text = nick

    }

    override fun showOpponentAvatar(uri: Uri) {
        TODO("Not yet implemented")
    }

    override fun showOpponentNick(nick: String) {
        TODO("Not yet implemented")
    }

    override fun connected() {

        if (fragmentLostConnection.visibility == View.VISIBLE) {

            fragmentLostConnection.visibility = View.GONE

        }

    }

    override fun disconnected() {

        if (fragmentLostConnection.visibility == View.GONE) {

            fragmentLostConnection.visibility = View.VISIBLE

        }

        tvWaiting.text = getString(R.string.lost_connection)

    }


}