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
        view?.showLoading()
        getGames.execute().subscribeAndAddToDisposables (
            {
                view?.populateList(it.toMutableList())
                view?.hideLoading()
            },
            {
                view?.showError()
                view?.hideLoading()
            }
        )
    }

    fun onGameClicked() {
        view?.showRunDetailScreen()
    }

    interface View : Presenter.View {
        fun initializeViews()
        fun populateList(games: MutableList<Game>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun showRunDetailScreen()
    }
}