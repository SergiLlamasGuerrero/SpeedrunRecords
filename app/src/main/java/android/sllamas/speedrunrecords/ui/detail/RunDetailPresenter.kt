package android.sllamas.speedrunrecords.ui.detail

import android.sllamas.speedrunrecords.ui.common.Presenter
import android.sllamas.usecases.GetRunsByGameId
import android.sllamas.usecases.GetUserNameById

class RunDetailPresenter(
    private val getRunsByGameId: GetRunsByGameId,
    private val getUserNameById: GetUserNameById
) : Presenter<RunDetailPresenter.View>() {

    override fun onViewAttached() {
        view?.initializeViews()
    }

    fun onReadyToGetRunInfo(gameId: String) {
        getRunsByGameId.execute(gameId).subscribeAndAddToDisposables(
            {
                val run = it.minBy { run -> run.time }
                run?.time?.let { time -> view?.showRunTime(time) }
                run?.videoUrl?.let { url -> view?.showVideoButton(url) }
                val playerName = run?.playerName
                val playerId = run?.playerId
                if (playerName != null) {
                    view?.showPlayerName(playerName)
                } else if (playerId != null) {
                    getUserNameById.execute(playerId).subscribeAndAddToDisposables(
                        { name -> view?.showPlayerName(name) },
                        { println("ERROR getting user name with id:$playerId") }
                    )
                }
            },
            {
                println("ERROR: Something went wrong getting run info with game is $gameId")
            }
        )
    }

    interface View : Presenter.View {
        fun initializeViews()
        fun showPlayerName(playerName: String)
        fun showRunTime(time: Long)
        fun showVideoButton(url: String)
    }
}