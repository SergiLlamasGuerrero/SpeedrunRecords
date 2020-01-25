package android.sllamas.speedrunrecords.common

abstract class Presenter<V : Presenter.View> {

    var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    interface View
}