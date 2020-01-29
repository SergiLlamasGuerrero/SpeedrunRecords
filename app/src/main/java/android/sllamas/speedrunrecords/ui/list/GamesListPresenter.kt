package android.sllamas.speedrunrecords.ui.list

import android.sllamas.speedrunrecords.ui.common.Presenter
import android.sllamas.speedrunrecords.ui.list.model.GameViewEntity
import android.sllamas.speedrunrecords.ui.list.model.transformToUi
import android.sllamas.usecases.GetGames

class GamesListPresenter(
    private val getGames: GetGames
) : Presenter<GamesListPresenter.View>() {

    override fun onViewAttached() {
        view?.initializeViews()
    }

    fun onViewsInitialized() {
        view?.showLoading()
        getGames.execute().subscribeAndAddToDisposables(
            {
                view?.populateList(it.map { game -> game.transformToUi() }.toMutableList())
                view?.hideLoading()
            },
            {
                view?.showError()
                view?.hideLoading()
            }
        )
    }

    fun onGameClicked(game: GameViewEntity) {
        view?.showRunDetailScreen(game)
    }

    interface View : Presenter.View {
        fun initializeViews()
        fun populateList(games: MutableList<GameViewEntity>)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun showRunDetailScreen(game: GameViewEntity)
    }
}