package android.sllamas.speedrunrecords.ui.common

abstract class Presenter<V : Presenter.View> {

    var view: V? = null

    fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    protected abstract fun onViewAttached()

    fun detachView() {
        view = null
    }

    interface View
}