package android.sllamas.usecases

import android.sllamas.data.repository.RunsRepository
import io.reactivex.Scheduler

class GetRunsByGameId(
    private val gameRepository: RunsRepository,
    private val subscribeThread: Scheduler,
    private val observeThread: Scheduler
) {

    fun execute(gameId: String) = gameRepository.getRuns(gameId).subscribeOn(subscribeThread).observeOn(observeThread)
}
