package android.sllamas.speedrunrecords.ui

import android.sllamas.speedrunrecords.ui.list.GamesListPresenter
import android.sllamas.usecases.GetGames
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GamesListPresenterTest {

    lateinit var getGamesUseCase: GetGames
    lateinit var presenter: GamesListPresenter
    lateinit var view: GamesListPresenter.View

    @Before
    fun setUp() {
        getGamesUseCase = mockk(relaxed = true)
        view = mockk(relaxed = true)
        presenter = GamesListPresenter(getGamesUseCase)
    }

    @Test
    fun `should initialize views when presenter is attached`() {
        presenter.attachView(view)

        verify { view.initializeViews() }
    }

    @Test
    fun `should populate list when get games use case is successful`() {
        every { getGamesUseCase.execute() } returns Single.just(mutableListOf())
        presenter.attachView(view)
        presenter.onViewsInitialized()

        verify { view.populateList(any()) }
    }

    @Test
    fun `should show error when get games use case is not successful`() {
        every { getGamesUseCase.execute() } returns Single.error(Throwable())
        presenter.attachView(view)
        presenter.onViewsInitialized()

        verify { view.showError() }
    }

    @Test
    fun `should show loading when views are initialized`() {
        presenter.attachView(view)
        presenter.onViewsInitialized()

        verify { view.showLoading() }
    }

    @Test
    fun `should hide loading when get games use case is successful`() {
        every { getGamesUseCase.execute() } returns Single.just(mutableListOf())
        presenter.attachView(view)
        presenter.onViewsInitialized()

        verify { view.hideLoading() }
    }

    @Test
    fun `should hide loading when get games use case is not successful`() {
        every { getGamesUseCase.execute() } returns Single.error(Throwable())
        presenter.attachView(view)
        presenter.onViewsInitialized()

        verify { view.hideLoading() }
    }
}