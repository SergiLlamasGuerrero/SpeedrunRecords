package android.sllamas.speedrunrecords.ui

import android.sllamas.speedrunrecords.ui.detail.RunDetailPresenter
import android.sllamas.usecases.GetRunsByGameId
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class RunDetailPresenterTest {

    lateinit var getRunsByGameId: GetRunsByGameId
    lateinit var presenter: RunDetailPresenter
    lateinit var view: RunDetailPresenter.View

    @Before
    fun setUp() {
        getRunsByGameId = mockk()
        view = mockk(relaxed = true)
        presenter = RunDetailPresenter(getRunsByGameId)
    }

    @Test
    fun `should initialize views when presenter is attached`() {
        presenter.attachView(view)

        verify { view.initializeViews() }
    }
}