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

            },
            {
                
            }
        )
    }

    interface View : Presenter.View {
        fun initializeViews()
    }
}