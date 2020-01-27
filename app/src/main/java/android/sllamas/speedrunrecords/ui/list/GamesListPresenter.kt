package android.sllamas.speedrunrecords.ui.list

import android.sllamas.domain.Game
import android.sllamas.speedrunrecords.ui.common.Presenter
import android.sllamas.usecases.GetGames

class GamesListPresenter(
    private val getGames: GetGames
) : Presenter<GamesListPresenter.View>() {

    override fun onViewAttached() {
        view?.initializeViews()
    }

    fun onViewsInitialized() {
        getGames.execute().subscribeAndAddToDisposables (
            {
                view?.populateList(it.toMutableList())
            },
            {
                view?.showError()
            }
        )
    }

    interface View : Presenter.View {
        fun initializeViews()
        fun populateList(games: MutableList<Game>)
        fun showError()
    }
}