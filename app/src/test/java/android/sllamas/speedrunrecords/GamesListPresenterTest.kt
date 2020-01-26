package android.sllamas.speedrunrecords

import android.sllamas.speedrunrecords.list.GamesListPresenter
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GamesListPresenterTest {

    lateinit var presenter: GamesListPresenter
    lateinit var view: GamesListPresenter.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = GamesListPresenter()
    }

    @Test
    fun `should initialize views when presenter is attached`() {
        presenter.attachView(view)

        verify { view.initializeViews() }
    }

    @Test
    fun `should populate list when views are initialized`() {
        presenter.attachView(view)
        presenter.onViewsInitialized()

        verify { view.populateList(any()) }
    }
}