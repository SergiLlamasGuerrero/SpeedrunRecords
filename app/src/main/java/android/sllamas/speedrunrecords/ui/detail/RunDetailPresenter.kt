package android.sllamas.speedrunrecords.ui.detail

import android.sllamas.speedrunrecords.ui.common.Presenter
import android.sllamas.usecases.GetRunsByGameId

class RunDetailPresenter(
    private val getRunsByGameId: GetRunsByGameId
) : Presenter<RunDetailPresenter.View>() {

    override fun onViewAttached() {
        view?.initializeViews()
    }

    fun onReadyToGetRunInfo(gameId: String) {
        getRunsByGameId.execute(gameId).subscribeAndAddToDisposables(
            {
                val run = it.minBy { run -> run.time }
                run?.playerName?.let { name -> view?.showPlayerName(name) }
                run?.time?.let { time -> view?.showRunTime(time) }
                run?.videoUrl?.let { url -> view?.showVideoButton(url) }
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