package com.example.disikgame.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.disikgame.R
import com.example.disikgame.activities.LobbyActivity.Companion.gameProvider
import com.example.disikgame.activities.LobbyActivity.Companion.playerProvider
import com.example.disikgame.http_client.ClientRequest
import com.example.disikgame.http_client.HttpClient
import com.example.disikgame.models.Player
import com.example.disikgame.presenters.game.GameInfoPresenter
import com.example.disikgame.presenters.game.OpponentInfoPresenter
import com.example.disikgame.presenters.game.PlayerInfoPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.providers.OpponentProvider
import com.example.disikgame.providers.PlayerProvider
import com.example.disikgame.views.game.GameInfo
import com.example.disikgame.views.game.OpponentInfo
import com.example.disikgame.views.game.PlayerInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.Thread.sleep


class GameActivity : MvpAppCompatActivity(), PlayerInfo, OpponentInfo, GameInfo {


    companion object {

        internal var opponentProvider = OpponentProvider()

    }

    @InjectPresenter
    lateinit var opponentInfoPresenter: OpponentInfoPresenter


    @InjectPresenter
    lateinit var playerInfoPresenter: PlayerInfoPresenter

    @InjectPresenter
    lateinit var gameInfoPresenter: GameInfoPresenter

    val handler = Handler {

        when (it.what) {

            1 -> gameInfoPresenter.waitingTheOpponent()
            2 -> gameInfoPresenter.lostConnection()
            3 -> gameInfoPresenter.gameIsGoing()
            4 -> gameInfoPresenter.opponentIsGuessing()
            5 -> gameInfoPresenter.opponentIsGuess()

        }

        true

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        playerInfoPresenter.showPlayerAvatar()
        playerInfoPresenter.showPlayerNick()
        opponentInfoPresenter.showOpponentAvatar()
        opponentInfoPresenter.showOpponentNick()

        gameInfoPresenter.setOpponentScore()
        gameInfoPresenter.setRaceTo()
        gameInfoPresenter.setPlayerScore()

        Thread {

            sleep(1000)
            this.handler.sendEmptyMessage(1)
            sleep(2500)
            this.handler.sendEmptyMessage(2)
            sleep(2500)
            this.handler.sendEmptyMessage(3)
            sleep(20)
            this.handler.sendEmptyMessage(4)
            sleep(2500)
            this.handler.sendEmptyMessage(5)

        }.start()


    }

    // player info

    override fun showPlayerAvatar(uri: Uri) =
        Picasso.with(applicationContext).load(uri).into(imgPlayerAvatar)


    override fun showPlayerNick(nick: String) =
        tvPlayerNick.setText(nick)

    // opponent info

    override fun showOpponentAvatar(uri: Uri) =
        Picasso.with(applicationContext).load(uri).into(imgOpponentAvatar)

    override fun showOpponentNick(nick: String) =
        tvOpponentNick.setText(nick)

    // game info

    override fun setPlayerScore(score: Int) =
        tvPlayerScore.setText(score.toString())

    override fun setOpponentScore(score: Int) =
        tvOpponentScore.setText(score.toString())

    override fun playerIsGuessing() {
        tvPlayerGuess.visibility = View.VISIBLE
        tvOpponentGuess.visibility = View.GONE
    }

    override fun playerIsGuess() {
    }

    override fun opponentIsGuessing() {
        tvPlayerGuess.visibility = View.GONE
        tvOpponentGuess.visibility = View.VISIBLE
        tvOpponentIsGuessing.visibility = View.VISIBLE
    }

    override fun opponentIsGuess() {
        tvOpponentIsGuessing.visibility = View.GONE
        fragmentGuess.visibility = View.VISIBLE
    }

    override fun setRaceTo(raceTo: Int) =
        tvRaceTo.setText("Race to ${raceTo}")

    override fun waitingTheOpponent() {

        fragmentLostConnection.visibility = View.VISIBLE
        tvWaiting.text = getString(R.string.waiting_the_opponent)

    }

    override fun lostConnection() {

        fragmentLostConnection.visibility = View.VISIBLE
        tvWaiting.text = getString(R.string.lost_connection)

    }

    override fun gameIsGoing() {

        fragmentLostConnection.visibility = View.GONE
        tvWaiting.text = getString(R.string.lost_connection)

    }


}