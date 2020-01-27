package android.sllamas.speedrunrecords.ui.common

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class Presenter<V : Presenter.View> {

    var view: V? = null
    private var disposables = CompositeDisposable()

    fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }

    protected abstract fun onViewAttached()

    fun detachView() {
        disposables.dispose()
        view = null
    }

    fun <T> Single<T>.subscribeAndAddToDisposables(
        onSuccess: (T) -> Unit = {},
        onError: (throwable: Throwable) -> Unit = {}
    ): Disposable {
        val disposable = this.subscribe({ onSuccess(it) }, { onError(it) })
        disposables.add(disposable)
        return disposable
    }

    interface View
}