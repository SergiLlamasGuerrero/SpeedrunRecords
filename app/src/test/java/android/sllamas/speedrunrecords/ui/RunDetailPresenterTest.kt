package android.sllamas.speedrunrecords.ui

import android.sllamas.speedrunrecords.ui.detail.RunDetailPresenter
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class RunDetailPresenterTest {

    lateinit var presenter: RunDetailPresenter
    lateinit var view: RunDetailPresenter.View

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        presenter = RunDetailPresenter()
    }

    @Test
    fun `should initialize views when presenter is attached`() {
        presenter.attachView(view)

        verify { view.initializeViews() }
    }
}