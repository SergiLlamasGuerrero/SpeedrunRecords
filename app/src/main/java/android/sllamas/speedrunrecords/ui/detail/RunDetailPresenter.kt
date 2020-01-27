package android.sllamas.speedrunrecords.ui.detail

import android.sllamas.speedrunrecords.ui.common.Presenter

class RunDetailPresenter : Presenter<RunDetailPresenter.View>() {

    override fun onViewAttached() {
        view?.initializeViews()
    }

    interface View : Presenter.View {
        fun initializeViews()
    }
}