package android.sllamas.speedrunrecords.list

import android.sllamas.speedrunrecords.common.Presenter
import android.sllamas.speedrunrecords.list.model.Game

class GamesListPresenter : Presenter<GamesListPresenter.View>() {

    override fun onViewAttached() {
        view?.initializeViews()
    }

    fun onViewsInitialized() {
        view?.populateList(mutableListOf())
    }

    interface View : Presenter.View {
        fun initializeViews()
        fun populateList(games: MutableList<Game>)
    }
}