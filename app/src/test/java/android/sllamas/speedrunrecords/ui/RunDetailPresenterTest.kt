package android.sllamas.speedrunrecords.ui

import android.sllamas.domain.Run
import android.sllamas.speedrunrecords.ui.detail.RunDetailPresenter
import android.sllamas.usecases.GetRunsByGameId
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RunDetailPresenterTest {

    lateinit var getRunsByGameId: GetRunsByGameId
    lateinit var presenter: RunDetailPresenter
    lateinit var view: RunDetailPresenter.View

    @Before
    fun setUp() {
        getRunsByGameId = mockk(relaxed = true)
        view = mockk(relaxed = true)
        presenter = RunDetailPresenter(getRunsByGameId)
    }

    @Test
    fun `should initialize views when presenter is attached`() {
        presenter.attachView(view)

        verify { view.initializeViews() }
    }

    @Test
    fun `should show record when get runs use case is successful and runs list is not empty`() {
        every { getRunsByGameId.execute(any()) } returns Single.just(listOf(getRun(time = 10)))
        presenter.attachView(view)
        presenter.onReadyToGetRunInfo("")

        verify { view.showRunTime(10) }
    }

    @Test
    fun `should not show record when get runs use case is successful but runs list is empty`() {
        every { getRunsByGameId.execute(any()) } returns Single.just(listOf())
        presenter.attachView(view)
        presenter.onReadyToGetRunInfo("")

        verify(exactly = 0) { view.showRunTime(any()) }
    }

    @Test
    fun `should show player name when get runs use case is successful and player name is not null`() {
        every { getRunsByGameId.execute(any()) } returns Single.just(listOf(getRun(playerName = "")))
        presenter.attachView(view)
        presenter.onReadyToGetRunInfo("")

        verify { view.showPlayerName("") }
    }

    @Test
    fun `should not show player name when get runs use case is successful and player name is null`() {
        every { getRunsByGameId.execute(any()) } returns Single.just(listOf(getRun(playerName = null)))
        presenter.attachView(view)
        presenter.onReadyToGetRunInfo("")

        verify(exactly = 0) { view.showPlayerName(any()) }
    }

    @Test
    fun `should show video button when get runs use case is successful and video url is not null`() {
        every { getRunsByGameId.execute(any()) } returns Single.just(listOf(getRun(videoUrl = "")))
        presenter.attachView(view)
        presenter.onReadyToGetRunInfo("")

        verify { view.showVideoButton("") }
    }

    @Test
    fun `should not show video button when get runs use case is successful and video url is null`() {
        every { getRunsByGameId.execute(any()) } returns Single.just(listOf(getRun(videoUrl = null)))
        presenter.attachView(view)
        presenter.onReadyToGetRunInfo("")

        verify(exactly = 0) { view.showVideoButton(any()) }
    }

    private fun getRun(
        playerId: String? = null,
        playerName: String? = null,
        time: Long = 0,
        videoUrl: String? = null
    ) = Run(playerId, playerName, time, videoUrl)
}