package android.sllamas.usecases

import android.sllamas.data.repository.GameRepository
import io.reactivex.Scheduler

class GetGames(
    private val gameRepository: GameRepository,
    private val subscribeThread: Scheduler,
    private val observeThread: Scheduler
) {

    fun execute() = gameRepository.getGames().subscribeOn(subscribeThread).observeOn(observeThread)
}
