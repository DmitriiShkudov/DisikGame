package com.example.disikgame.activities

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
import com.example.disikgame.presenters.game.GameInfoPresenter
import com.example.disikgame.presenters.game.OpponentInfoPresenter
import com.example.disikgame.presenters.game.PlayerInfoPresenter
import com.example.disikgame.providers.GameProvider
import com.example.disikgame.views.game.GameInfo
import com.example.disikgame.views.game.OpponentInfo
import com.example.disikgame.views.game.PlayerInfo
import com.squareup.picasso.Picasso
import com.squareup.picasso.Request
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : MvpAppCompatActivity(), PlayerInfo, OpponentInfo, GameInfo {

    companion object {

        const val OPPONENT_IS_CONNECTED = 0
        const val UPDATE = 1

    }


    private val onChoseBtnClickListener = View.OnClickListener {

        var number = -1

        if (GameProvider.PlayerProvider.guessing) {

            when (it) {

                chooseBtn1 -> number = 1
                chooseBtn2 -> number = 2
                chooseBtn3 -> number = 3
                chooseBtn4 -> number = 4
                chooseBtn5 -> number = 5

            }

            GameProvider.apply {

                guessedNumber = number
                GameProvider.PlayerProvider.guessed = true
                GameProvider.OpponentProvider.choosing = true


            }
            HttpClient.sendRequest(ClientRequest.GAME_INFO.first)

        }

        if (GameProvider.PlayerProvider.choosing) {

            when (it) {

                chooseBtn1 -> number = 1
                chooseBtn2 -> number = 2
                chooseBtn3 -> number = 3
                chooseBtn4 -> number = 4
                chooseBtn5 -> number = 5

            }

            GameProvider.apply {

                chosenNumber = number
                GameProvider.PlayerProvider.chose = true
                GameProvider.OpponentProvider.guessing = true

            }
            HttpClient.sendRequest(ClientRequest.GAME_INFO.first)

        }

    }


    @InjectPresenter
    lateinit var opponentInfoPresenter: OpponentInfoPresenter

    @InjectPresenter
    lateinit var playerInfoPresenter: PlayerInfoPresenter

    @InjectPresenter
    lateinit var gameInfoPresenter: GameInfoPresenter

    val handler = Handler {

        when (it.what) {

            OPPONENT_IS_CONNECTED -> {

                Log.d("Opponent is connected", ".")

                Toast.makeText(applicationContext, "Opponent is connected", Toast.LENGTH_SHORT).show()

                gameInfoPresenter.gameIsGoing()
                playerInfoPresenter.showPlayerAvatar()
                playerInfoPresenter.showPlayerNick()
                opponentInfoPresenter.showOpponentNick()
                opponentInfoPresenter.showOpponentAvatar()
                gameInfoPresenter.setRaceTo()


            }

            UPDATE -> {

                Log.d("Update", ".")

                Toast.makeText(applicationContext, "Update", Toast.LENGTH_SHORT).show()

                gameInfoPresenter.setPlayerScore()
                gameInfoPresenter.setOpponentScore()
                gameInfoPresenter.playerIsGuessing()
                gameInfoPresenter.playerIsGuess()
                gameInfoPresenter.opponentIsGuessing()
                gameInfoPresenter.opponentIsGuess()

            }

        }

        true

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        HttpClient.gameHandler = this.handler

        when (intent.action) {

            Response.NewPlayer.FIRST_PLAYER_ADDED ->
                gameInfoPresenter.waitingTheOpponent()

            Response.NewPlayer.GAME_IS_STARTED -> {
                gameInfoPresenter.gameIsGoing()
                playerInfoPresenter.showPlayerAvatar()
                playerInfoPresenter.showPlayerNick()
                opponentInfoPresenter.showOpponentNick()
                opponentInfoPresenter.showOpponentAvatar()
            }

        }

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
        tvOpponentNick.setText(GameProvider.OpponentProvider.nick)

    // game info

    override fun setPlayerScore(score: Int) =
        tvPlayerScore.setText(score.toString())

    override fun setOpponentScore(score: Int) =
        tvOpponentScore.setText(score.toString())

    override fun playerIsGuessing(guessing: Boolean) {
        tvPlayerGuess.visibility = View.VISIBLE
        tvOpponentGuess.visibility = View.GONE
    }

    override fun playerIsGuess(guessed: Boolean) {
        tvPlayerGuess.visibility = View.GONE
        fragmentGuess.visibility = View.VISIBLE
    }

    override fun opponentIsGuessing(guessing: Boolean) {
        tvPlayerGuess.visibility = View.GONE
        tvOpponentGuess.visibility = View.VISIBLE
        tvOpponentIsGuessing.visibility = View.VISIBLE
    }

    override fun opponentIsGuess(guessed: Boolean) {
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

        Log.d("Game is going", "shit")
        fragmentLostConnection.visibility = View.GONE
        Toast.makeText(applicationContext, "Game is started", Toast.LENGTH_SHORT).show()

    }


}